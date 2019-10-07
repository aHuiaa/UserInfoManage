package com.pc.service.impl;

import com.pc.dao.UserDao;
import com.pc.dao.impl.UserDaoImpl;
import com.pc.domain.PageBean;
import com.pc.domain.User;
import com.pc.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getU_username(),user.getU_password());
    }

    @Override
    public List<User> finAll() {
        return dao.findAll();
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }


    @Override
    public void deleteUser(int u_id) {
        dao.deleteUser(u_id);
    }

    @Override
    public User findUser(int u_id) {
        return dao.findUser(u_id);
    }

    @Override
    public void deleteUsers(String[] u_ids) {
        for (int i = 0; i<u_ids.length;i++){
        dao.deleteUser(Integer.parseInt(u_ids[i]));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //如果用户点击的页码大于总页码设当前页码为总页码，如果用户点击的页码小于总页码，当前页码设为最小页码
        if (currentPage<1){
            currentPage = 1;
        }
        //1.创建一个空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //4.计算总页码
        int totalPage = ((totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1);
        pb.setTotalPage(totalPage);

        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        pb.setCurrentPage(currentPage);

        //5.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        if (start <0){
            start = 0;
            rows = 0;
        }
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        return pb;
    }

}
