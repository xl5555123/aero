package com.tencent.aero.controller;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.controller.exception.ResourceNotFoundException;
import com.tencent.aero.model.Music;
import com.tencent.aero.model.UserMusic;
import com.tencent.aero.model.MusicResult;
import com.tencent.aero.repository.UserMusicRepository;
import com.tencent.aero.service.UserMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/usermusic")
public class UserMusicController {

    public final static String NO_PERMISSION_FORMAT = "This usermusic %s is exist";

    @Autowired
    private UserMusicService userMusicService;

    @RequestMapping(value = "/{uin}", method = RequestMethod.GET)
    public List<MusicResult> pushLike(@PathVariable String uin,
                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return userMusicService.pushUserLike(uin, pageable);
    }

    @RequestMapping(value = "/get/{uin}", method = RequestMethod.GET)
    public List<MusicResult> recommend(@PathVariable String uin) {
        return userMusicService.recommend(uin);
    }
/*
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User addUser(@RequestBody(required = true) User user) {
        User newUser = userService.addUser(user);
        if (newUser == null) {
            throw new NoPermissionException(String.format(NO_PERMISSION_FORMAT, user));
        }
        return newUser;
    }
*/
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public UserMusic updateUser(@RequestBody  UserMusic userMusic) {
        return userMusicService.updateTag(userMusic);
    }



}
