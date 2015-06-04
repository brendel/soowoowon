package com.soowoowon.controller;

import com.soowoowon.model.Photo;
import com.soowoowon.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 사진첩 콘트롤러. {@link com.soowoowon.service.PhotoService}에서 사진들을 읽어서 뷰에 넘겨준다.
 *
 * @see com.soowoowon.service.PhotoService
 */
@Controller
@RequestMapping("/gallery")
public class GalleryController {
    @Autowired
    PhotoService photoService;

    @RequestMapping(method = RequestMethod.GET)
    public String gallery(Model model) {
        List<Photo> photoList = photoService.list();
        model.addAttribute("photoList", photoList);
        return "gallery";
    }
}
