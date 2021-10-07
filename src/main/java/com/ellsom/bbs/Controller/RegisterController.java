package com.ellsom.bbs.Controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.Util.AjaxResult;
import com.ellsom.bbs.Util.DateUtils;
import com.ellsom.bbs.Pojo.Vo.RegisterVO;
import com.ellsom.bbs.Pojo.Po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private IUserService iUserService;

    @PostMapping("/forumApi/register")
    public AjaxResult regist(@RequestBody RegisterVO registerVO) {
        User user = new User();
        user.setUserName(registerVO.getUserName());
        user.setPassWord(SaSecureUtil.md5(SaSecureUtil.sha1(registerVO.getPassWord())));
        user.setEmail(registerVO.getEmail());
        user.setPhoneNumber(registerVO.getPhoneNumber());
        user.setLoginDate(DateUtils.getNowDate());
        int i = iUserService.insertUser(user);
        //注册成功返回值为1，失败为0
        if (i > 0) {
            return AjaxResult.success("注册成功", i);
        } else {
            return AjaxResult.error("注册失败", i);
        }
    }

}
