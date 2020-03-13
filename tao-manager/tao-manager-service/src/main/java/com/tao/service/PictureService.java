package com.tao.service;

import com.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    public PictureResult uploadPicture(MultipartFile uploadFile);
}
