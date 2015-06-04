package com.soowoowon.service;

import com.soowoowon.dao.PhotoDAO;
import com.soowoowon.exception.PhotoUploadException;
import com.soowoowon.model.Photo;
import com.soowoowon.viewModel.UploadedPhoto;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link com.soowoowon.service.PhotoService}의 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Photo
 * @see com.soowoowon.service.PhotoService
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoDAO photoDAO;

    @Value("#{environment['local.storage']}")
    private String localStorage;

    @Override
    public Photo getPhoto(int id) {
        return photoDAO.get(id);
    }

    /**
     * 클라이언트가 업로드한 사진(MultipartFile)을 파일시스템에 저장.
     *
     * @param photo
     * @param uploadedPhoto
     * @throws PhotoUploadException
     */
    @Override
    public void savePhoto(Photo photo, UploadedPhoto uploadedPhoto) throws PhotoUploadException {
        MultipartFile multipartFile = uploadedPhoto.getPhoto();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        String imageExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        String month = dateFormat.format(new Date());
        String randomString = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

        String photoFileName = randomString + "." + imageExtension;
        String photoUri = "/files/photo/" + month + "/" + photoFileName;
        String photoFilePath = localStorage + "/photo/" + month + "/" + photoFileName;

        String thumbFileName = randomString + "_thumb." + imageExtension;
        String thumbUri = "/files/thumb/" + month + "/" + thumbFileName;
        String thumbFilePath = localStorage + "/thumb/" + month + "/" + thumbFileName;

        // TODO: Extract all FileIOs into service
        try {
            File file = new File(photoFilePath);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (IOException e) {
            throw new PhotoUploadException("사진 업로드가 실패했습니다: ", e);
        }

        try {
            File originalFile = new File(photoFilePath);
            BufferedImage bufferedImage = ImageIO.read(originalFile);

            final ImageMetadata metadata = Imaging.getMetadata(originalFile);
            if (metadata instanceof JpegImageMetadata) {
                JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

                TiffImageMetadata exif = jpegMetadata.getExif();
                if (exif != null) {
                    short[] field = exif.getFieldValue(TiffTagConstants.TIFF_TAG_ORIENTATION);
                    int imageOrientation = field == null ? -1 : field[0];
                    if (imageOrientation == 3 || imageOrientation == 6 || imageOrientation == 8) {
                        bufferedImage = rotateImage(bufferedImage, imageOrientation);
                    }
                }
            }
            bufferedImage = resizeImage(bufferedImage, 1080);
            BufferedImage thumbnailImage = generateThumbnail(bufferedImage, 82, 82);

            File thumbnailFile = new File(thumbFilePath);
            if (!thumbnailFile.getParentFile().exists()) {
                thumbnailFile.getParentFile().mkdirs();
            }
            ImageIO.write(bufferedImage, imageExtension, originalFile);
            ImageIO.write(thumbnailImage, imageExtension, thumbnailFile);

        } catch (IOException e) {
            throw new PhotoUploadException("이미지 프로세싱 에러.", e);
        } catch (ImageReadException e) {
            e.printStackTrace();
        }
        photo.setFileName(photoFileName);
        photo.setUri(photoUri);
        photo.setThumbnailUri(thumbUri);
        photo.setPublished(new Date());
        photoDAO.add(photo);
    }

    /**
     * 사진과 썸네일을 파일 시스템과 DB에서 삭제한다.
     *
     * @param photo
     */
    @Override
    public void deletePhoto(Photo photo) {
        String photoFilePath = localStorage + photo.getUri();
        String thumbFilePath = localStorage + photo.getThumbnailUri();
        FileUtils.deleteQuietly(new File(photoFilePath));
        FileUtils.deleteQuietly(new File(thumbFilePath));
        photoDAO.delete(photo);
    }

    @Override
    public List<Photo> list() {
        return photoDAO.list();
    }

    /**
     * 이미지 크기를 줄인다.
     * <p>축소되길 원하는 이미지의 사이즈보다 원본 이미지의 사이즈가 작으면 아무것도 수행하지 않고
     * 원본 이미지의 레퍼런스를 그대로 리턴한다.
     *
     * @param sourceImage 원본 이미지
     * @param definition  축소 될 이미지의 가로 세로 길이 중 짧은 쪽 픽셀.
     * @return
     */
    private BufferedImage resizeImage(BufferedImage sourceImage, int definition) {
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();

        BufferedImage resizedImage;

        if (width > height && height > definition) {
            resizedImage = Scalr.resize(sourceImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_HEIGHT, definition);
        } else if (width <= height && width > definition) {
            resizedImage = Scalr.resize(sourceImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, definition);
        } else {
            resizedImage = sourceImage;
        }

        return resizedImage;
    }

    /**
     * 주어진 크기로 썸네일을 만든다.
     *
     * @param originalImage 원본 이미지
     * @param width         썸네일 가로 픽셀
     * @param height        썸네일 세로 픽셀
     * @return 썸네일 이미지
     */
    private BufferedImage generateThumbnail(BufferedImage originalImage, int width, int height) {
        int originalImageWidth = originalImage.getWidth();
        int originalImageHeight = originalImage.getHeight();

        int cropMargin = (int) Math.abs(Math.round(((originalImageWidth - originalImageHeight) / 2.0)));

        int x0 = 0;
        int y0 = 0;
        int side = 0;

        if (originalImageWidth > originalImageHeight) {
            x0 = cropMargin;
            side = originalImageHeight;
        } else {
            y0 = cropMargin;
            side = originalImageWidth;
        }

        BufferedImage cropedImage = Scalr.crop(originalImage, x0, y0, side, side);
        BufferedImage thumbnailImage = Scalr.resize(cropedImage, Scalr.Method.QUALITY, width, height);

        return thumbnailImage;
    }

    /**
     * 이미지를 회전시킨다.
     * <p>이미지의 EXIF 데이터에 Orientation이 명시되어 있는 경우 몇몇 웹브라우저를 포함한 가벼운 이미지 뷰어에서
     * 이미지의 방향을 인식하지 못하는 경우가 있다. 그런 경우를 막기 위해 EXIF 데이터의 orientation에 따라 이미지를 회전시킨다.
     *
     * @param src
     * @param orientation
     * @return
     */
    private BufferedImage rotateImage(BufferedImage src, int orientation) {
        BufferedImage result = src;
        switch (orientation) {
            case 3:
                result = Scalr.rotate(src, Scalr.Rotation.CW_180);
                break;
            case 6:
                result = Scalr.rotate(src, Scalr.Rotation.CW_90);
                break;
            case 8:
                result = Scalr.rotate(src, Scalr.Rotation.CW_270);
                break;
        }
        return result;
    }

    public String getLocalStorage() {
        return localStorage;
    }

    public void setLocalStorage(String localStorage) {
        this.localStorage = localStorage;
    }
}