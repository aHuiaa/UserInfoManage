package com.pc.dao.impl;

import com.pc.dao.UserDao;
import com.pc.domain.User;
import com.pc.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl extends JDBCUtils implements UserDao{

    private JdbcTemplate template = new JdbcTemplate(getDataSource());

    @Override
    public List<User> findAll(){
        String sql = "select * from t_user";
        List<User> users = template.query(sql,new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into t_user values(null,?,?,?,?,?,?,null,null )";
        template.update(sql,user.getU_nikename(),user.getU_gender(),user.getU_age(),user.getU_address(),user.getU_qq(),user.getU_email());

    }

    @Override
    public void updateUser(User user) {
        String sql = "update t_user set u_nikename=?,u_gender=?,u_age=?,u_address=?,u_qq=?,u_email=?,u_username=null,u_password=null where u_id=?";
        template.update(sql,user.getU_nikename(),user.getU_gender(),user.getU_age(),user.getU_address(),user.getU_qq(),user.getU_email(),user.getU_id());
    }

    @Override
    public void deleteUser(int u_id) {
        try {
            String sql = "delete from t_user where u_id = ?";
            template.update(sql,u_id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUser(int u_id) {
        String sql = "select * from t_user where u_id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), u_id);
        return user;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from t_user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet){

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null &&  !"".equals(value)){

                sb.append("  and "+ key + " like ? " );
                params.add("%"+value+"%");
            }
        }
        sql = sb.toString();
//        System.out.println(sql);
//        System.out.println(params);
        return template.queryForObject(sql,Integer.class,params.toArray());
//        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
//        String sql = "select * from t_user limit ?,?";
        //1、定义模板初始化sql
        String sql = "select * from t_user where 1 = 1  ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map 获取到查询条件
        Set<String> keySet = condition.keySet();
        List<Object> params = new ArrayList<>();
       for (String key : keySet){

           //排除分页条件参数
           if("currentPage".equals(key) || "rows".equals(key)){
               continue;
           }
           //获取value
           String value = condition.get(key)[0];
           if (value != null || !"".equals(value)){
               sb.append(" and " + key +" like ? ");
               params.add("%"+value+"%");
           }
       }
       sb.append(" limit ?,?");
       params.add(start);
       params.add(rows);
        sql = sb.toString();
//        System.out.println(sql+"  ---findByPage---  ");
//        System.out.println(params);
        return template.query(sql,new BeanPropertyRowMapper<>(User.class),params.toArray());
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from t_user where u_username =? and u_password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
