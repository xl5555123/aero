package com.tencent.aero.controller;

import com.tencent.aero.model.Singer;
import com.tencent.aero.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/singers")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Singer> getAll() {
        return singerService.getAll();
    }

    @RequestMapping(value = "/{singerId}", method = RequestMethod.GET)
    public Singer getOne(@PathVariable Long singerId) {
        return singerService.getById(singerId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Singer addSinger(@RequestBody Singer singer) {
        return singerService.addSinger(singer);
    }

    @RequestMapping(value = "/{singerId}", method = RequestMethod.PUT)
    public Singer updateSinger(@PathVariable Long singerId, @RequestBody Singer singer) {
        singer.setId(singerId);
        return singerService.update(singer);
    }

    @RequestMapping(value = "/{singerId}", method = RequestMethod.DELETE)
    public void deleteSinger(@PathVariable Long singerId) {
        singerService.deleteById(singerId);
    }

}
