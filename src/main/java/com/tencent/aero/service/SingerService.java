package com.tencent.aero.service;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.model.Singer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SingerService {
    Singer getById(Long id);

    Singer update(Singer singer);

    void deleteById(Long id);

    List<Singer> getAll();

    Singer addSinger(Singer singer) throws NoPermissionException;
}
