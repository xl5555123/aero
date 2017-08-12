package com.tencent.aero.repository;

import com.tencent.aero.model.Singer;
import com.tencent.aero.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface UserRepository extends Repository<User, Long> {
    List<User> findAll();

    User findById(Long id);

    User findByUin(String uin);

    User save(User user);

    void deleteById(Long id);

    void deleteByUin(String uin);

}
