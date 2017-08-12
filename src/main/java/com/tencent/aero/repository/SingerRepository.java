package com.tencent.aero.repository;

import com.tencent.aero.model.Singer;
import com.tencent.aero.model.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface SingerRepository extends Repository<Singer, Long> {
    List<Singer> findAll();

    Singer findById(Long id);

    Page<Singer> findByIsMan(Boolean isMan, Pageable pageable);

    List<Singer> findByName(String name);

    Singer save(Singer singer);

    void deleteById(Long id);


}
