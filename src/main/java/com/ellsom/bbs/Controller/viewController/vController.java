package com.ellsom.bbs.Controller.viewController;

import com.ellsom.bbs.Service.IUserService;
import com.ellsom.bbs.util.AjaxResult;
import com.ellsom.bbs.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Controller
public class vController {
    @Autowired
    private IUserService iUserService;

    @ResponseBody
    @PostMapping("/xiaopopan/eduoss/fileoss/upload/1413125531471233026")
    public AjaxResult setAvator(MultipartFile file){
        UploadUtil.checkFileSize(file);
        UploadUtil.checkSubffix(file.getOriginalFilename(), UploadUtil.uploadConfig.imageSuffix);
        String httpUrl = UploadUtil.saveFile(file, UploadUtil.uploadConfig.imageFolder);
        System.out.println(httpUrl);
        HashMap<String,Object> map=new HashMap<>();
        HashMap<String, String> fileMap = new HashMap<>();
        fileMap.put("url",httpUrl);
        fileMap.put("filetype","image");
        map.put("file",fileMap);
        return AjaxResult.success(map);
    }

}
