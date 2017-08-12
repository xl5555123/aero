package com.tencent.aero.controller;

import com.tencent.aero.controller.exception.ResourceNotFoundException;
import com.tencent.aero.model.File;
import com.tencent.aero.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/api/files", method = RequestMethod.POST)
    public File uploadFile(@RequestParam(value = "file", required = true)MultipartFile file) {
        return fileService.saveFile(file);
    }

    @RequestMapping(value = "/api/files/{fileId}", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        byte[] fileByte = fileService.readFileById(fileId);
        if (fileByte == null) {
            throw new ResourceNotFoundException();
        }
        String fileName = fileService.getFileNameById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=" + fileName.replace(" ", "_"))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileByte);
    }

    @RequestMapping(value = "/api/images/{fileId}", method = RequestMethod.GET)
    public HttpEntity<byte[]> showImage(@PathVariable Long fileId) {
        if (!fileService.isFileExist(fileId)) {
            throw new ResourceNotFoundException();
        }
        byte[] fileByte = fileService.readFileById(fileId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileByte);
    }

    @RequestMapping(value = "/api/files/{fileId}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable Long fileId) {
        boolean succeed = fileService.deleteFileById(fileId);
        if (!succeed) {
            throw new ResourceNotFoundException();
        }
    }
}
