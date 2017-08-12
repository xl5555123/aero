package com.tencent.aero.service;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.controller.exception.ResourceNotFoundException;
import com.tencent.aero.model.FullMusic;

import java.util.List;

public interface MusicService {

    FullMusic addMusic(FullMusic fullMusic) throws NoPermissionException;

    /**
     * 存在歌手按歌手查，不存在歌手若存在风格按风格查，否则全量
     * @param styleId
     * @param singerId
     * @return
     */
    List<FullMusic> getMusicByStyleIdOfSingerIdOrNot(Long styleId, Long singerId, int page);

    List<FullMusic> getAll();

    void deleteMusicById(Long musicId) throws ResourceNotFoundException;

    FullMusic getById(Long id);

    FullMusic updateMusic(FullMusic fullMusic);
}
