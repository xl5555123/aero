package com.tencent.aero.service;

import com.tencent.aero.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface UserService {
    /**
     * 查询user
     * @return
     */
    List<User> getAllUsers();

    /**
     * 通过uin查询user
     * @param uin
     * @return
     */
    User getUserByUin(String uin);

    /**
     * 通过id查询user
     * @param id
     * @return user或空（当id不存在的时候）
     */
    User getUserById(Long id);

    /**
     * 增加风格
     * @param user
     * @return 返回新创建的用户，若风格已存在返回null
     */
    User addUser(User user);

    /**
     * 通过uin删除
     * @param uin 若不存在则不删除
     */
    void deleteUser(String uin);

    /**
     * 通过风格id更新风格
     * 会更新所有已绑定歌曲的风格
     * @param 用户uin
     * @param 用户新的信息
     * @return 若用户名存在id不存在则返回null
     * @return 成功则返回新的用户
     */
    User updateUser(String uin, User user);
}
