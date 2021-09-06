package com.ellsom.bbs.Controller;

import com.ellsom.bbs.Service.ITagService;
import com.ellsom.bbs.pojo.po.Tag;
import com.ellsom.bbs.util.AjaxResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/forumApi/tag")
public class TagController {


    @Autowired
    private ITagService iTagService;

    @GetMapping("/list")
    public AjaxResult selectTags(@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 Tag dqTag){
        //System.out.println("ID为："+dqTag.getTagId());
        List<Tag> dqTags = iTagService.selectTags(pageNum, pageSize, dqTag);
        return AjaxResult.success("查询成功", new PageInfo<>(dqTags));
    }

}
