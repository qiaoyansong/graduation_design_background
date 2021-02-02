package com.qiaoyansong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/1/31 21:40
 * description：
 */
@Controller
public class TestController {
    @RequestMapping(path = "/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
