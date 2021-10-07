package com.ellsom.bbs.Pojo.Po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 标签的ID */
    @TableId(value = "tag_id",type= IdType.AUTO)
    private Long tagId;

    /** 标签的名称 */
    @TableField(value = "tag_name")
    private String tagName;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}

