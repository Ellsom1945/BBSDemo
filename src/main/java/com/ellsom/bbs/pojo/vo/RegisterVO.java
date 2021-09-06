package com.ellsom.bbs.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 前端参数要与属性名相同
     * 例如用户名要传username，不能userName
     * 后面将会更新
     */
    /** 用户账户 **/
    private String userName;
    /** 密码 **/
    private String passWord;
    /** 邮箱 **/
    private String email;
    /** 头像 **/
    private String avatar;
    /**
     * 手机号
     */
    private String phoneNumber;

}