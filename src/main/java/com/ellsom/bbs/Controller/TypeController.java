package com.ellsom.bbs.Controller;

import com.ellsom.bbs.Service.ITypeService;
import com.ellsom.bbs.pojo.po.Type;
import com.ellsom.bbs.util.AjaxResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/forumApi/dqtype")
public class TypeController {
    @Autowired
    private ITypeService iTypeService;

    @GetMapping("/list")

    public AjaxResult list(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           Type dqType) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        List<Type> dqTypes = iTypeService.selectAllTypes(pageNum, pageSize, dqType);
        PageInfo<Type> pageInfo = new PageInfo<>(dqTypes);
        return AjaxResult.success("查询成功", pageInfo);
    }

    @GetMapping("/{dqTypeId}")
    public AjaxResult getInfo(@PathVariable Long dqTypeId) {
        Type dqType = iTypeService.selectTypeById(dqTypeId);
        return AjaxResult.success("查询成功", dqType);
    }
}
