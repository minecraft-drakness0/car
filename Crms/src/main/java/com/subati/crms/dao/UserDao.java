package com.subati.crms.dao;

import com.subati.crms.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用的是mybatis框架，用来数据库的增、删、改、查操作
 */
@Repository
public interface UserDao {
    //    会根据用户输入的用户名和密码来获取信息，方法名getUserByMassage
    public User getUserByMassage(@Param("username") String username, @Param("password") String password);
    public List<User> getAllUser(@Param("username") String username, @Param("pageStart") int PageStart,@Param("pageSize") int pageSize);
    public int getUserCounts(@Param("username") String username);
    public int updateState(Integer id,Boolean state);
    public int addUser(User user);
    public int deleteUser(int id);
    public User getUpdateUser(int id);
    public int editUser(User user);
}
