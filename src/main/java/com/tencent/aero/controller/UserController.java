package com.tencent.aero.controller;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.controller.exception.ResourceNotFoundException;
import com.tencent.aero.model.User;
import com.tencent.aero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    public final static String NO_PERMISSION_FORMAT = "This user %s is exist";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getOne(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User addUser(@RequestBody(required = true) User user) {
        User newUser = userService.addUser(user);
        if (newUser == null) {
            throw new NoPermissionException(String.format(NO_PERMISSION_FORMAT, user));
        }
        return newUser;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody  String newUin, @PathVariable Long userId) {
        User toChangeUser = userService.getUserById(userId);
        if (toChangeUser == null) {
            throw new ResourceNotFoundException();
        }
        User existUser = userService.getUserByUin(newUin);
        if (existUser != null) {
            throw new NoPermissionException(String.format(NO_PERMISSION_FORMAT, newUin));
        }
        return userService.updateUser(newUin, toChangeUser);
    }

    @RequestMapping(value = "/{uin}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable String uin) {
        userService.deleteUser(uin);
    }



}
