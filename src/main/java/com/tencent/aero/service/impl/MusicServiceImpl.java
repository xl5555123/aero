package com.tencent.aero.service.impl;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.controller.exception.ResourceNotFoundException;
import com.tencent.aero.model.*;
import com.tencent.aero.repository.*;
import com.tencent.aero.service.FileService;
import com.tencent.aero.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class MusicServiceImpl implements MusicService {

    public final static int MUSIC_COUNT_PER_PAGE = 5;

    @Autowired
    private FileService fileService;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private MusicStyleRepository musicStyleRepository;

    @Autowired
    private MusicSingerRepository musicSingerRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private StyleRepository styleRepository;

    private ConcurrentHashMap<Long, Singer> idSingerCache = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Long, Style> idStyleCache = new ConcurrentHashMap<>();

    private <T>  boolean isEmpty(List<T> list) {
        return list == null ? true : list.size() == 0;
    }

    @Override
    public FullMusic addMusic(FullMusic fullMusic) {
        if (fullMusic.getAttachedFileId() == null || !fileService.isFileExist(fullMusic.getAttachedFileId()) || isEmpty(fullMusic.getSingers()) || isEmpty(fullMusic.getStyles())) {
            throw new NoPermissionException();
        }
        fullMusic.setCreated(new Date());
        fullMusic.setUpdated(new Date());
        Music music = new Music(fullMusic);

        Music savedMusic = musicRepository.save(music);
        Long musicId = savedMusic.getId();
        fullMusic.setId(musicId);
        for (Style style : fullMusic.getStyles()) {
            MusicStyle musicStyle = new MusicStyle();
            musicStyle.setMusicId(musicId);
            musicStyle.setStyleId(style.getId());
            musicStyleRepository.save(musicStyle);
        }
        for (Singer singer : fullMusic.getSingers()) {
            MusicSinger musicSinger = new MusicSinger();
            musicSinger.setMusicId(musicId);
            musicSinger.setSingerId(singer.getId());
            musicSingerRepository.save(musicSinger);
        }
        return fullMusic;
    }

    @Override
    public List<FullMusic> getMusicByStyleIdOfSingerIdOrNot(Long styleId, Long singerId, int page) {
        clearCache();
        Sort sort = new Sort(Sort.Direction.DESC, "musicId");
        Pageable pageable = new PageRequest(page, MUSIC_COUNT_PER_PAGE, sort);
        List<Music> musics = new ArrayList<>();
        if (singerId != null) {
            Page<MusicSinger> musicSingers = musicSingerRepository.findBySingerId(singerId, pageable);
            Page<Long> ids = musicSingers.map(new Converter<MusicSinger, Long>() {
                @Override
                public Long convert(MusicSinger musicSinger) {
                    return musicSinger.getId();
                }
            });
            musics = musicRepository.findByIdIsIn(ids.getContent());
        }
        else if (styleId != null) {
            Page<MusicStyle> musicStyles = musicStyleRepository.findByStyleId(styleId, pageable);
            Page<Long> ids = musicStyles.map(new Converter<MusicStyle, Long>() {
                @Override
                public Long convert(MusicStyle musicStyle) {
                    return musicStyle.getId();
                }
            });
            musics = musicRepository.findByIdIsIn(ids.getContent());
        }
        else {
            pageable = new PageRequest(page, MUSIC_COUNT_PER_PAGE, new Sort(Sort.Direction.ASC, "id"));
            musics = musicRepository.findAll(pageable).getContent();
        }
        return fillMusics(musics);
    }

    private List<FullMusic> fillMusics(List<Music> musics) {
        List<FullMusic> fullMusics = new ArrayList<>();
        for (Music music : musics) {
            fullMusics.add(fillMusic(music));
        }
        return fullMusics;
    }

    private FullMusic fillMusic(Music music) {
        FullMusic fullMusic = new FullMusic(music);
        List<MusicSinger> musicSingers = musicSingerRepository.findByMusicId(music.getId());
        List<MusicStyle> musicStyles = musicStyleRepository.findByMusicId(music.getId());
        List<Singer> singers = new ArrayList<>();
        for (MusicSinger musicSinger : musicSingers) {
            Singer singer = null;
            if (idSingerCache.containsKey(musicSinger.getSingerId())) {
                singer = idSingerCache.get(musicSinger.getSingerId());
            }
            else {
                singer = singerRepository.findById(musicSinger.getSingerId());
                idSingerCache.put(singer.getId(), singer);
            }
            singers.add(singer);
        }
        fullMusic.setSingers(singers);
        List<Style> styles = new ArrayList<>();
        for (MusicStyle musicStyle : musicStyles) {
            Style style = null;
            if (idStyleCache.containsKey(musicStyle.getStyleId())) {
                style = idStyleCache.get(musicStyle.getStyleId());
            }
            else {
                style = styleRepository.findById(musicStyle.getStyleId());
                idStyleCache.put(style.getId(), style);
            }
            styles.add(style);
        }
        fullMusic.setStyles(styles);
        return fullMusic;
    }

    private void clearCache() {
        idSingerCache.clear();
        idStyleCache.clear();
    }

    @Override
    public List<FullMusic> getAll() {
        clearCache();
        List<Music> musics = musicRepository.findAll();
        return fillMusics(musics);
    }

    @Override
    public void deleteMusicById(Long musicId) {
        Music music = musicRepository.findOne(musicId);
        if (music == null) {
            throw new ResourceNotFoundException();
        }
        musicSingerRepository.deleteByMusicId(musicId);
        musicStyleRepository.deleteByMusicId(musicId);
        musicRepository.deleteById(musicId);
    }

    @Override
    public FullMusic getById(Long id) {
        clearCache();
        Music music = musicRepository.findOne(id);
        if (music == null) {
            throw new ResourceNotFoundException();
        }
        return fillMusic(music);
    }

    @Override
    public FullMusic updateMusic(FullMusic fullMusic) {
        Music originMusic = musicRepository.findOne(fullMusic.getId());
        fullMusic.setAttachedFileId(originMusic.getAttachedFileId());
        fullMusic.setUpdated(new Date());
        musicRepository.save(new Music(fullMusic));

        List<MusicStyle> musicStyles = musicStyleRepository.findByMusicId(fullMusic.getId());
        List<Long> styleIds = new ArrayList<>();
        for (MusicStyle musicStyle : musicStyles) {
            styleIds.add(musicStyle.getStyleId());
        }
        musicStyleRepository.deleteByMusicIdAndStyleIdNotIn(fullMusic.getId(), styleIds);
        List<MusicSinger> musicSingers = musicSingerRepository.findByMusicId(fullMusic.getId());
        List<Long> singerIds = new ArrayList<>();
        for (MusicSinger musicSinger : musicSingers) {
            singerIds.add(musicSinger.getSingerId());
        }
        musicSingerRepository.deleteByMusicIdAndSingerIdNotIn(fullMusic.getId(), singerIds);
        clearCache();
        return fillMusic(fullMusic);
    }




}
