package edufarm.service;

import edufarm.model.Photo;
import edufarm.viewModel.UploadedPhoto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@link UploadedPhoto}를 처리하고 클라이언트에 돌려보내는 JSON 응답을 생성하는 서비스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Photo
 * @see UploadedPhoto
 */
@Service
public class PhotoResponseService {

    public String getSuccessReseponse(Photo photo, MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("photoId", photo.getId());
        jsonObject.put("name", photo.getFileName());
        jsonObject.put("size", file.getSize());
        jsonObject.put("url", photo.getUri());
        jsonObject.put("thumbnailUrl", photo.getThumbnailUri());
        jsonObject.put("deleteUrl", "/api/photo/" + photo.getId());
        jsonObject.put("deleteType", "DELETE");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);

        JSONObject ret = new JSONObject();
        ret.put("files", jsonArray);
        return ret.toJSONString();
    }

    public String getFailedResponse(MultipartFile file, BindingResult result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", file.getOriginalFilename());
        jsonObject.put("size", file.getSize());
        jsonObject.put("error", result.getFieldError("photo"));

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);

        JSONObject ret = new JSONObject();
        ret.put("files", jsonArray);
        return ret.toJSONString();
    }

    public String getDeleteResponse(Photo photo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(photo.getFileName(), true);
        jsonObject.put("photoId", photo.getId());

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);

        JSONObject ret = new JSONObject();
        ret.put("files", jsonArray);
        return ret.toJSONString();
    }
}
