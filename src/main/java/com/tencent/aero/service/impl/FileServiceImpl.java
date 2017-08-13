package com.tencent.aero.service.impl;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.tencent.aero.model.File;
import com.tencent.aero.repository.FileRepository;
import com.tencent.aero.service.FileService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    public final static org.slf4j.Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${aero.path}")
    private String aeroPath;

    @Autowired
    private FileRepository fileRepository;

    private String getName(String origin, String suffix) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((origin + new Date().getTime()).getBytes());
            String newFileName = String.format("%s.%s", new BigInteger(1, md.digest()).toString(16), suffix);
            return newFileName;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = getName(fileName, suffix);
        String filePath = aeroPath + newFileName;
        logger.info(String.format("save file in %s", filePath));
        java.io.File savedFile = new java.io.File(filePath);
        try {
            savedFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(savedFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            logger.info(String.format("save file in %s finished!", filePath));
            File newFileItem = new File();
            newFileItem.setName(newFileName);
            return fileRepository.save(newFileItem);
        } catch (IOException e) {
                e.printStackTrace();
        }

        return null;
    }

    @Override
    public byte[] readFileById(Long fileId) {
        File file = fileRepository.findById(fileId);
        if (file == null) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(aeroPath + file.getName());
            return org.apache.commons.io.IOUtils.toByteArray(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteFileById(Long fileId) {
        File file = fileRepository.findById(fileId);
        if (file == null) {
            return false;
        }
        fileRepository.deleteById(fileId);
        java.io.File realFile = new java.io.File(aeroPath + file.getName());
        realFile.delete();
        return true;
    }

    @Override
    public boolean isFileImage(Long fileId) {File file = fileRepository.findById(fileId);
        if (file == null) {
            return false;
        }
        java.io.File realFile = new java.io.File(aeroPath + file.getName());
        String mimetype= new MimetypesFileTypeMap().getContentType(realFile);
        String type = mimetype.split("/")[0];
        if(type.equals("image")) {
            return true;
        }
        return false;
    }

    @Override
    public String getFileNameById(Long fileId) {
        File file = fileRepository.findById(fileId);
        return file == null ? null : file.getName();
    }

    @Override
    public boolean isFileExist(Long fileId) {
        File file = fileRepository.findById(fileId);
        if (file != null) {
            java.io.File realFile = new java.io.File(aeroPath + file.getName());
            if (realFile.exists()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public byte[] getMp3Image(Long fileId) throws InvalidDataException, IOException, UnsupportedTagException {
        File file = fileRepository.findById(fileId);
        if (!file.getName().contains("mp3")) {
            return null;
        }
        Mp3File song = new Mp3File(aeroPath + file.getName());
        if (song.hasId3v2Tag()){
            ID3v2 id3v2tag = song.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            //converting the bytes to an image
            return imageData;
        }
        return null;
    }
}
