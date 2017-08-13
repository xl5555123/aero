package com.tencent.aero.repository;

import com.tencent.aero.model.UserMusic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserMusicRepository extends PagingAndSortingRepository<UserMusic, Long> {
    List<UserMusic> findAll();

    List<UserMusic> findById(Long id);

    List<UserMusic> findByUin(String uin);

    Page<UserMusic> findByuinAndTag(String uin, Boolean tag, Pageable pageable);

    List<UserMusic> findByuinAndTag(String uin, Boolean tag);

    List<UserMusic> findByMusicId(Long musicId);

    UserMusic findByuinAndMusicId(String uin, Long musicId);

    UserMusic save(UserMusic userMusic);

    void deleteById(Long id);

    void deleteByuin(String uin);

    void deleteByMusicId(Long musicId);

    List<UserMusic> findTop10ByuinAndTag(String uin, Boolean tag);
}
