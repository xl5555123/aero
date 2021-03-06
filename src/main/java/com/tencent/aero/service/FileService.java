package com.tencent.aero.service;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.tencent.aero.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface FileService {
    File saveFile(MultipartFile file);

    byte[] readFileById(Long fileId);

    boolean deleteFileById(Long fileId);

    boolean isFileImage(Long fileId);

    String getFileNameById(Long fileId);

    boolean isFileExist(Long fileId);

    byte[] getMp3Image(Long fileId) throws InvalidDataException, IOException, UnsupportedTagException;

}
