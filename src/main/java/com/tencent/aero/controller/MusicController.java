package com.tencent.aero.controller;

import com.tencent.aero.model.FullMusic;
import com.tencent.aero.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/songs")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FullMusic> getMusic(@RequestParam(value = "page", required = true, defaultValue = "1") int page,
                                    @RequestParam(value = "styleId", required = false) Long styleId,
                                    @RequestParam(value = "singerId", required = false) Long singerId) {
        return musicService.getMusicByStyleIdOfSingerIdOrNot(styleId, singerId, page - 1);
    }

    @RequestMapping(value = "/{songId}", method = RequestMethod.GET)
    public FullMusic getOne(@PathVariable Long songId) {
        return musicService.getById(songId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public FullMusic addMusic(@RequestBody FullMusic fullMusic) {
        return musicService.addMusic(fullMusic);
    }

    @RequestMapping(value = "/{songId}", method = RequestMethod.PUT)
    public FullMusic updateMusic(@RequestBody FullMusic fullMusic) {
        return musicService.updateMusic(fullMusic);
    }

    @RequestMapping(value = "/{songId}", method = RequestMethod.DELETE)
    public void deleteMusic(@PathVariable Long songId) {
        musicService.deleteMusicById(songId);
    }
}
