package com.tencent.aero.service.impl;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.model.Singer;
import com.tencent.aero.repository.SingerRepository;
import com.tencent.aero.service.FileService;
import com.tencent.aero.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private FileService fileService;

    @Override
    public Singer getById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Singer update(Singer singer) {
        Singer originSinger = singerRepository.findById(singer.getId());
        singer.setAttachedFileId(originSinger.getAttachedFileId());
        singer.setUpdated(new Date());
        singer.setCreated(originSinger.getCreated());
        return singerRepository.save(singer);
    }

    @Override
    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public List<Singer> getAll() {
        return singerRepository.findAll();
    }

    @Override
    public Singer addSinger(Singer singer) {
        Long fileId = singer.getAttachedFileId();
        if (!fileService.isFileExist(fileId)) {
            throw new NoPermissionException(String.format(("add singer %s failed, due to file not exist"), singer.getName()));
        }
        singer.setCreated(new Date());
        singer.setUpdated(new Date());
        return singerRepository.save(singer);
    }

}
