package com.tencent.aero.repository;

import com.tencent.aero.model.MusicSinger;
import com.tencent.aero.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MusicSingerRepository extends PagingAndSortingRepository<MusicSinger, Long> {

    Page<MusicSinger> findBySingerId(Long singerId, Pageable pageable);

    List<MusicSinger> findByMusicId(Long musicId);

    void deleteById(Long id);

    void deleteByMusicId(Long musicId);

    void deleteByIdNotIn(List<Long> ids);

    void deleteByMusicIdAndSingerIdNotIn(Long musicId, List<Long> singerIds);
}
