package com.ellsom.bbs.Service;

import com.ellsom.bbs.pojo.po.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IUserService {

    public List<User> selectAllUsers(int pageNum, int pageSize, User User) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;


    public User selectUserById(Long UserId);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    public User selectUserByUserName(String userName);

    /**
     * 根据账号密码查询用户（登陆功能）
     *
     * @param userName
     * @param password
     * @return
     */
    public User selectUserByUserNameAndPassword(String userName, String password);


    public int insertUser(User User);

    /**
     * 注销用户
     *
     * @param
     * @return
     */
    public int deleteUser();

    /**
     * 根据ID更改用户
     *
     * @return
     */
    public int updateUserById(User User);


    /**
     * 获取当前用户的权限
     *
     * @return
     */
    public String selectNowUserPermission();

}
