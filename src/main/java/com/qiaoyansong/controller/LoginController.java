package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/9 18:27
 * description：
 */

@Controller
public class LoginController {
    private static final Logger LOGGER  = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity login(@Valid @RequestBody User user, BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (allErrors.isEmpty()) {
            return this.userService.login(user);
        } else {
            // 有异常
            LOGGER.error("请求参数校验失败");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        }
    }
}
