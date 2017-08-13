package com.tencent.aero.service;

import com.tencent.aero.model.User;
import com.tencent.aero.model.UserMusic;
import com.tencent.aero.model.MusicResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserMusicService {
    /**
     * 查询user music
     * @return
     */
    List<MusicResult> pushUserLike(String uin , Pageable pageable);

    List<MusicResult> recommend(String uin);

    List<MusicResult> newUserRecommend(User user, int n, List<MusicResult> musicResults);

    List<MusicResult> oldUserRecommend(User user);

    UserMusic updateTag(UserMusic userMusic);


}
