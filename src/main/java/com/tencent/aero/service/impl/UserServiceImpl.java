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
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUin(String uin) {
        return userRepository.findByUin(uin);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User addUser(User user) {
        User existUser = userRepository.findByUin(user.getUin());
        if (existUser != null) {
            return existUser;
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String uin) {
        userRepository.deleteByUin(uin);
    }

    @Override
    public User updateUser(String uin, User user) {
        User existUser = userRepository.findByUin(uin);
        if (existUser == null) {
            return null;
        }
        return userRepository.save(user);
    }
}