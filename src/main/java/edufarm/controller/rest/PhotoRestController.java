package edufarm.controller.rest;

import edufarm.model.Photo;
import edufarm.model.User;
import edufarm.service.PhotoResponseService;
import edufarm.service.PhotoService;
import edufarm.service.UserService;
import edufarm.viewModel.UploadedPhoto;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;

/**
 * REST API. 사진의 업로드, 삭제.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Controller
@RequestMapping("/api/photo")
public class PhotoRestController {

    @Autowired
    PhotoService photoService;

    @Autowired
    PhotoResponseService photoResponseService;

    @Autowired
    UserService userService;

    /**
     * multipart/form-data 사진을 받아서 저장한다.
     * <p>Response는 {@link edufarm.service.PhotoResponseService}에 의해 JSON String으로 작성된다.
     * <p>성공 Response:
     * <ul>
     * <li>photoId</li>
     * <li>name</li>
     * <li>size</li>
     * <li>url</li>
     * <li>thumbnailUrl</li>
     * <li>deleteUrl</li>
     * <li>deleteType</li>
     * </ul>
     * <p>실패 Response.
     * <ul>
     * <li>name</li>
     * <li>size</li>
     * <li>error</li>
     * </ul>
     *
     * @param uploadedPhoto
     * @param result
     * @param principal
     * @return
     * @see UploadedPhoto
     */
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String handlePhotoUpload(@Valid UploadedPhoto uploadedPhoto, BindingResult result, Principal principal) {
        if (result.hasErrors())
            return photoResponseService.getFailedResponse(uploadedPhoto.getPhoto(), result);

        User user = userService.get(principal.getName());
        Photo photo = new Photo();
        photo.setUser(user);
        try {
            photoService.savePhoto(photo, uploadedPhoto);
        } catch (ImageReadException e) {
        }


        return photoResponseService.getSuccessReseponse(photo, uploadedPhoto.getPhoto());
    }

    /**
     * 사진({@link edufarm.model.Photo})의 삭제.
     * <p>HTTP DELETE 또는 HTTP POST 요청의 숨겨진 필드(_method: "DELETE")로 접근 가능.
     * Response:
     * <ul>
     * <li>photoId</li>
     * <li>name</li>
     * </ul>
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String handlePhotoDelete(@PathVariable int id) {
        Photo photo = photoService.getPhoto(id);
        String response = photoResponseService.getDeleteResponse(photo);
        photoService.deletePhoto(photo);
        return response;
    }
}
