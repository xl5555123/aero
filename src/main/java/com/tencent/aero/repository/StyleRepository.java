package com.tencent.aero.repository;

import com.tencent.aero.model.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface StyleRepository extends Repository<Style, Long> {
    List<Style> findAll();

    Style findById(Long id);

    Style findByName(String name);

    Style save(Style style);

    void removeById(Long id);

}
