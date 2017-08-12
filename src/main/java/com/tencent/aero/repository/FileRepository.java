package com.tencent.aero.repository;

import com.tencent.aero.model.File;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FileRepository extends Repository<File, Long> {

    List<File> findAll();

    File findById(Long id);

    List<File> findByName(String name);

    File save(File singer);

    void deleteById(Long id);
}
