package com.tencent.aero.repository;

import com.tencent.aero.model.UserPlayed;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserPlayedRepository extends PagingAndSortingRepository<UserPlayed, Long> {

    List<UserPlayed> findByid(Long id);

    List<UserPlayed> findByUin(String uin);

    UserPlayed save(UserPlayed userPlayed);
}
