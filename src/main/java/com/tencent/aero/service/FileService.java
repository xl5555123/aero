package com.tencent.aero.service;

import com.tencent.aero.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

public interface FileService {
    File saveFile(MultipartFile file);

    byte[] readFileById(Long fileId);

    boolean deleteFileById(Long fileId);

    boolean isFileImage(Long fileId);

    String getFileNameById(Long fileId);

    boolean isFileExist(Long fileId);

    BufferedImage getMp3Image(Long fileId);

}
