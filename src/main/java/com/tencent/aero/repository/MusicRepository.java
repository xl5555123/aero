package com.tencent.aero.repository;

import com.tencent.aero.model.Music;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MusicRepository  extends PagingAndSortingRepository<Music, Long> {
    List<Music> findByIdIsIn(List<Long> id);

    List<Music> findAll();

    Music save(Music music);

    Music deleteById(Long id);


}
