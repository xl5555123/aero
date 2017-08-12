package com.tencent.aero.repository;

import com.tencent.aero.model.MusicSinger;
import com.tencent.aero.model.MusicStyle;
import com.tencent.aero.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MusicStyleRepository extends PagingAndSortingRepository<MusicStyle, Long> {

    Page<MusicStyle> findByStyleId(Long styleId, Pageable pageable);

    void deleteById(Long id);

    List<MusicStyle> findByMusicId(Long musicId);

    void deleteByMusicId(Long musicId);

    void deleteByMusicIdAndStyleIdNotIn(Long musicId, List<Long> styleIds);

}

