package com.wpw.mybatislearn.mapper;

import com.wpw.mybatislearn.entity.User;

import java.util.List;

/**
 * userMapper类，和db进行交互
 *
 * @author wpw
 */
public interface UserMapper {
    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return 用户user信息
     */
    User searchUser(Integer id);

    /**
     * 获取用户列表
     *
     * @return userList
     */
    List<User> listUser();

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 是否插入成功
     */
    Integer insert(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 是否更新成功
     */
    Integer update(User user);

    /**
     * 根据用户id删除用户信息
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    Integer delete(Integer id);
}
