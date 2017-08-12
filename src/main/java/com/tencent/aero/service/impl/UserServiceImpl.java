package com.tencent.aero.service.impl;

import com.tencent.aero.model.User;
import com.tencent.aero.repository.UserRepository;
import com.tencent.aero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * {@link UserService}
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    @Override
    public User getUserByUin(String uin) {
        return UserRepository.findByUin(uin);
    }

    @Override
    public User getUserById(Long id) {
        return UserRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        User existUser = UserRepository.findByUin(user.getUin());
        if (existUser != null) {
            return existUser;
        }
        return UserRepository.save(user);
    }

    @Override
    public void deleteUser(String uin) {
        UserRepository.deleteByUin(uin);
    }

    @Override
    public User updateUser(String uin, User user) {
        User existUser = UserRepository.findByUin(uin);
        if (existUser == null) {
            return null;
        }
        return UserRepository.save(user);
    }
}