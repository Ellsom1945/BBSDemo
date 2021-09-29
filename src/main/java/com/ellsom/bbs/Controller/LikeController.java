package com.ellsom.bbs.Controller;

import com.ellsom.bbs.Service.IlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forumApi/dqlike")
public class LikeController {
    @Autowired
    private IlikeService ilikeService;
}
