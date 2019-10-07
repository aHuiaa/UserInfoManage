package com.pc.dao;

import com.pc.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {

    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 查询全部用户列表
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param u_id
     */
    void deleteUser(int u_id);

    /**
     * 根据用户id查找用户
     * @param u_id
     * @return
     */
    User findUser(int u_id);

    /**
     * 查询总的记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
