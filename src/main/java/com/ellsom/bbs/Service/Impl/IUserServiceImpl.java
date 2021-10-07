package com.ellsom.bbs.Service.Impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ellsom.bbs.Mapper.UserMapper;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.Util.DateUtils;
import com.ellsom.bbs.Util.StringUtils;
import com.ellsom.bbs.Pojo.Po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllUsers(int pageNum, int pageSize, User user) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        Page<User>page = new Page<>(pageNum,pageSize);
        Page<User> selectPage = userMapper.selectPage(page, null);
        return selectPage.getRecords();
    }

    @Override
    public User selectUserById(Long UserId) {

        return userMapper.selectById(UserId);
    }

    @Override
    public User selectUserByUserName(String userName) {
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("user_name",userName);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User selectUserByUserNameAndPassword(String userName, String password) {
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("user_name",userName).eq("password",password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int insertUser(User User) {
        return userMapper.insert(User);
    }

    @Override
    public int deleteUser() {
        Long id= StpUtil.getLoginIdAsLong();
        return userMapper.deleteById(id);
    }

    @Override
    public int updateUserById(User User) {
        User oldOne=userMapper.selectById(User.getUserId());
        oldOne.setNickName(User.getNickName());
        oldOne.setEmail(User.getEmail());
        oldOne.setPhoneNumber(User.getPhoneNumber());
        oldOne.setSex(User.getSex());
        oldOne.setAvatar(User.getAvatar());
        if (StringUtils.isNotEmpty(User.getPassWord())) {
            oldOne.setPassWord(SaSecureUtil.md5(SaSecureUtil.sha1(User.getPassWord())));
        }
        oldOne.setSignature(User.getSignature());

        oldOne.setLoginDate(DateUtils.getNowDate());
        oldOne.setUpdateTime(DateUtils.getNowDate());
        int i=userMapper.updateById(User);
        return i;
    }

    @Override
    public String selectNowUserPermission() {
        return null;
    }
}
