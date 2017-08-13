package com.tencent.aero.repository;

import com.tencent.aero.model.CountMusic;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CountMusicRepository extends PagingAndSortingRepository<CountMusic, Long> {

    List<CountMusic> findAllByOrderByCountDesc();

    CountMusic findByid(Long id);

    CountMusic save(CountMusic countMusic);
}
