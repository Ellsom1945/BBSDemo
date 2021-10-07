package com.ellsom.bbs.Pojo.Po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    /** 用户账号 */
    @TableField(value = "user_name")
    private String userName;

    /** 用户昵称 */
    @TableField(value = "nick_name")
     private String nickName;

    /** 用户邮箱 */
    @TableField(value = "email")
    private String email;

    /** 手机号码 */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /** 用户性别 0=男,1=女,2=未知*/
    @TableField(value = "sex")
    private String sex;

    /** 用户头像 */
    @TableField(value = "avatar")
    private String avatar;

    /** 密码 */
    @TableField(value = "password")
    private String passWord;

    /** 帐号状态（0正常 1停用） */
    @TableField(value = "status")
    private String status;

    /** 删除标志（0代表存在 1代表删除,暂时没打算用） */
    @TableField(value = "delFlag",exist = false)
    private String delFlag;

    /** 最后登录IP */
    @TableField(value = "loginIp",exist = false)
    private String loginIp;

    /** 最后登录时间 */
    @TableField(value = "loginDate")
    private Date loginDate;

    /** 用户角色ID */
    @TableField(value = "role",exist = false)
    private String role;

    /** 个性签名 **/
    @TableField(value = "signature")
    private String signature;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

