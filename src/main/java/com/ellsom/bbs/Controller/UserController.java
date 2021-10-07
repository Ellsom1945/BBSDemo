package com.ellsom.bbs.Controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.Pojo.Po.User;
import com.ellsom.bbs.Util.AjaxResult;
import com.ellsom.bbs.Util.HttpStatus;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/forumApi/dquser")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/list")
    public AjaxResult list(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           User user) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<User> users = iUserService.selectAllUsers(pageNum, pageSize, user);
        if (users.isEmpty()) {
            return AjaxResult.error(HttpStatus.NOT_FOUND, "未查询到用户");
        }
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return AjaxResult.success("查询成功", pageInfo);
    }

    @GetMapping("/getnowuser")
    public AjaxResult getNowUser() {
        long nowUserId = StpUtil.getLoginIdAsLong();
        User user = iUserService.selectUserById(nowUserId);
        return AjaxResult.success("查询成功", user);
    }

    @GetMapping("/logout")
    public AjaxResult logout() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
            return AjaxResult.success("登出账号成功!");
        } else {
            return AjaxResult.error("您还未登陆!");
        }
    }

    @GetMapping("/{UserId}")
    public AjaxResult getInfo(@PathVariable Long UserId) {
        //后面可以根据缓存中先查询
        User user = iUserService.selectUserById(UserId);
        return AjaxResult.success("查询成功", user);
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody User newUser) {
//        DqUser dqUser = iUserService.selectDqUserById(newUser.getDquserid());
        User dqUser = new User();
        //更新用户
        dqUser.setUserId(newUser.getUserId());
        dqUser.setUserName(newUser.getUserName());
        dqUser.setNickName(newUser.getNickName());
        dqUser.setEmail(newUser.getEmail());
        dqUser.setPhoneNumber(newUser.getPhoneNumber());
        dqUser.setSex(newUser.getSex());
        dqUser.setAvatar(newUser.getAvatar());
        //加密密码
        dqUser.setPassWord(newUser.getPassWord());
        dqUser.setSignature(newUser.getSignature());
        System.out.println(dqUser);
        int i = iUserService.updateUserById(dqUser);
        return AjaxResult.success("更新成功", i);
    }

    @GetMapping("/delete")
    public AjaxResult deleteDqUser() {
        int i = iUserService.deleteUser();
        if (i > 0) {
            return AjaxResult.success("注销成功", i);
        } else {
            return AjaxResult.error("注销失败", i);
        }
    }
}
