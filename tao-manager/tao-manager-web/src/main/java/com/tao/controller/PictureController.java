package com.tao.controller;

import com.common.pojo.PictureResult;
import com.tao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public PictureResult shangchuan(MultipartFile uploadFile){
        PictureResult pictureResult = pictureService.uploadPicture(uploadFile);
        return pictureResult;
    }
}
