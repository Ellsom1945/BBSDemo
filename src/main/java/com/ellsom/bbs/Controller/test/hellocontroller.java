package com.ellsom.bbs.Controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hellocontroller {
    @GetMapping("/home")
    public String hello() {
        return "/";
    }

    @GetMapping("/test")
    public String qqq() {
        return "/upload-file/upload/image/2021/08-26/16299616168451834375672.png";
    }
}
