package com.ellsom.bbs.Controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.Util.AjaxResult;
import com.ellsom.bbs.Pojo.Vo.LoginVO;
import com.ellsom.bbs.Pojo.Po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/forumApi/login")
    public AjaxResult login(@RequestBody LoginVO loginVO) {
        User user = iUserService.selectUserByUserNameAndPassword(loginVO.getUserName(), SaSecureUtil.md5(SaSecureUtil.sha1(loginVO.getPassWord())));
        user.setPassWord("000000");
        //Assert.notNull(dqUser,"用户名密码错误");
        //Sa-Token登陆
        //TODO 登陆并使用数据库用户ID来作为Satoken的用户ID
        StpUtil.setLoginId(user.getUserId(), true);
//        StpUtil.setLoginId(dqUser.getUserName(),true);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        System.out.println(tokenInfo.tokenName);
        System.out.println(tokenInfo.tokenValue);
        return AjaxResult.success("success!", tokenInfo);
    }
}
