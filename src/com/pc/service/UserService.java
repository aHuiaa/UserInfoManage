package com.pc.service;

import com.pc.domain.PageBean;
import com.pc.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> finAll();

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param u_id
     */
    void deleteUser(int u_id);

    /**
     * 根据用户id 查询用户，将用户信息返回到更新用户信息列表
     * @param u_id
     * @return
     */
    User findUser(int u_id);

    /**
     * 批量删除用户
     * @param u_ids
     */
    void deleteUsers(String[] u_ids);

    /**
     * 分页 条件 查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
