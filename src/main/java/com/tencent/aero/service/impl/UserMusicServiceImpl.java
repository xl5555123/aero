package com.tencent.aero.service.impl;

import com.tencent.aero.model.*;
import com.tencent.aero.repository.*;
import com.tencent.aero.service.UserMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * {@link UserMusicService}
 */
@Service
@Transactional
public class UserMusicServiceImpl implements UserMusicService {

    @Autowired
    private UserMusicRepository userMusicRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountMusicRepository countMusicRepository;
    @Autowired
    private UserPlayedRepository userPlayedRepository;
    @Autowired
    private MusicStyleRepository musicStyleRepository;
/*
    @PostConstruct
    public void init() {
        for(long i = 0;i < 10;++i) {
            Music music = new Music();
            music.setId(i);
            music.setAttachedFileId(i);
            music.setChorusEnd(i);
            music.setChorusStart(i);
            music.setName("xxx");
            music.setTotalTime(i);
            musicRepository.save(music);
        }
        for (long i = 0;i < 10; ++i){
            MusicStyle musicStyle = new MusicStyle();
            musicStyle.setMusicId(i);
            musicStyle.setId(i);
            musicStyle.setStyleId(i);
            musicStyleRepository.save(musicStyle);
        }
    }
*/

    @Override
    public List<MusicResult> pushUserLike(String uin, Pageable pageable){
        List<MusicResult> lmr = new ArrayList<>();
        Page<UserMusic> pUserMusic = userMusicRepository.findByuinAndTag(uin, true, pageable);
        for(UserMusic userMusic : pUserMusic){
            Music music = musicRepository.findOne(userMusic.getMusicId());
            MusicResult mr = new MusicResult(userMusic, music);
            lmr.add(mr);
        }
        return lmr;
    }
    @Override
    public List<MusicResult> recommend(String uin) {
        List<MusicResult> musicResults = new ArrayList<>();
        User user = userRepository.findByUin(uin);
        List<UserMusic> userMusics = userMusicRepository.findByUin(uin);
        if(userMusics == null){
            return newUserRecommend(user, 0, musicResults);
        }
        else{
            return oldUserRecommend(user);
        }

    }
    @Override
    public List<MusicResult> newUserRecommend(User user, int n, List<MusicResult> musicResults) {
        List<MusicStyle> musicStyles = musicStyleRepository.findByStyleId(user.getStyleId());
        List<Music> musics = new ArrayList<>();
        java.util.Random r = new java.util.Random();
        Boolean flag = true;
        int musicStyleLen = musicStyles.size();
        while(flag && n < 7) {
            flag = false;
            int index = r.nextInt(musicStyleLen);
            Boolean found = false;
            for(Music music : musics) {
                if(music.getId() == musicStyles.get(index).getMusicId()) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                Music music = musicRepository.findOne(musicStyles.get(index).getMusicId());
                musics.add(music);
                flag = true;
                ++n;
            }
        }
        List<CountMusic> countMusics = countMusicRepository.findAllByOrderByCountDesc();

        for(CountMusic countMusic : countMusics){
            Boolean found = false;
            for(Music music : musics) {
                if(music.getId() == countMusic.getId()) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                Music music = musicRepository.findOne(countMusic.getId());
                musics.add(music);
                ++n;
            }
            if(n > 9)
                break;
        }
        flag = true;
        while (flag && n < 10)
        {
            flag = false;
            List<Music> allMusics = musicRepository.findAll();
            int mid = r.nextInt(allMusics.size());
            Boolean found = false;
            for(Music music : musics) {
                if(music.getId() == allMusics.get(mid).getId()) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                Music music = musicRepository.findOne(allMusics.get(mid).getId());
                musics.add(music);
                flag = true;
                ++n;
            }
        }
        //List<MusicResult> musicResults = new ArrayList<>();
        for(Music music : musics){
            UserMusic userMusic = userMusicRepository.findByuinAndMusicId(user.getUin(), music.getId());
            if (userMusic == null) {
                userMusic = new UserMusic(user.getUin(), music.getId(), false);
            }
            CountMusic countMusic = countMusicRepository.findByid(music.getId());
            if(countMusic == null) {
                countMusic = new CountMusic(1);
                countMusicRepository.save(countMusic);
            }
            else {
                countMusic.setCount(countMusic.getCount() + 1);
                countMusicRepository.save(countMusic);
            }
            MusicResult mr = new MusicResult(userMusic, music);
            musicResults.add(mr);
        }
        return musicResults;
    }
    @Override
    public List<MusicResult> oldUserRecommend(User user) {
        List<UserMusic> userMusics = userMusicRepository.findByuinAndTag(user.getUin(), true);
        List<MusicResult> musicResults = new ArrayList<>();
        java.util.Random r = new java.util.Random();
        if(userMusics == null)
        {
            return newUserRecommend(user ,0 , musicResults);
        }
        List<Music> allMusics = musicRepository.findAll();
        List<Music> musics = new ArrayList<>();
        int n = 0;
        int mid = r.nextInt(userMusics.size());
        List<UserMusic> userMusics1 = userMusicRepository.findByMusicId(userMusics.get(mid).getMusicId());
        for(Music music : allMusics){
            List<UserMusic> userMusics2 = userMusicRepository.findByMusicId(music.getId());
            if(userMusics2.size() == userMusics1.size())
            {
                ++n;
                musics.add(music);
            }
            if(n > 9)break;
        }
        if(n < 10)
        {
            for(Music music : musics){
                UserMusic userMusic = userMusicRepository.findByuinAndMusicId(user.getUin(), music.getId());
                if (userMusic == null) {
                    userMusic = new UserMusic(user.getUin(), music.getId(), false);
                }
                CountMusic countMusic = countMusicRepository.findByid(music.getId());
                if(countMusic == null) {
                    countMusic = new CountMusic(1);
                    countMusicRepository.save(countMusic);
                }
                else {
                    countMusic.setCount(countMusic.getCount() + 1);
                    countMusicRepository.save(countMusic);
                }
                MusicResult mr = new MusicResult(userMusic, music);
                musicResults.add(mr);
            }
            return newUserRecommend(user , n , musicResults);
        }
        //List<Music> musics = musicRepository.findAll();
        //List<User> users = userRepository.findAll();
        return musicResults;
    }
    @Override
    public UserMusic updateTag(UserMusic userMusic) {
        UserMusic existUserMusic = userMusicRepository.findByuinAndMusicId(userMusic.getUin(), userMusic.getMusicId());
        if (existUserMusic == null) {
            userMusicRepository.save(userMusic);
            return userMusic;
        }
        existUserMusic.setTag(userMusic.getTag());
        return userMusicRepository.save(existUserMusic);
    }

}
