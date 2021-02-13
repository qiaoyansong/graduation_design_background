package com.qiaoyansong.controller;

import com.qiaoyansong.dao.UserMapper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.StatusCode;
import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.front.Admin;
import com.qiaoyansong.service.AdminService;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.util.RequestContextHolderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity login(@Valid @RequestBody User user, BindingResult bindingResult) {
        LOGGER.info("进入LoginController.login");
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

    @RequestMapping(value = "/getSaveInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getSaveInfo() {
        ResponseEntity<User> responseEntity = new ResponseEntity<>();
        String userName = null;
        LOGGER.info("进入LoginController.getSaveInfo");
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        LOGGER.info("sessionId为" + session.getId());
        if (session.getAttribute("userName") == null) {
            LOGGER.info("session中没有userName信息");
            responseEntity.setBody(null);
        } else {
            LOGGER.info("session中有userName信息");
            userName = (String) session.getAttribute("userName");
            User user = this.userMapper.getUserInfo(userName);
            user.setPassword("");
            user.setSessionId("");
            responseEntity.setBody(user);
        }
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        return responseEntity;
    }

    @RequestMapping(value = "/removeSaveInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> removeSaveInfo() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        String userName = null;
        LOGGER.info("进入LoginController.removeSaveInfo");
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        LOGGER.info("sessionId为" + session.getId());
        if (session.getAttribute("userName") == null) {
            LOGGER.info("session中没有userName信息");
            responseEntity.setBody(null);
        } else {
            LOGGER.info("session中有userName信息,开始删除session中的信息");
            session.removeAttribute("userName");
        }
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        return responseEntity;
    }

    @RequestMapping(value = "/logout/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> logout(@Valid @NotNull(message = "用户名不能为空") @PathVariable("userName") String userName) {
        LOGGER.info("进入LoginController.logout");
        return this.userService.logout(userName);
    }

    @RequestMapping(value = "/login/admin/getVerificationCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getAdminVerificationCode(@Valid @RequestBody Admin admin, BindingResult bindingResult) {
        LOGGER.info("进入LoginController.getAdminVerificationCode");
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            LOGGER.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            return this.adminService.getAdminVerificationCode(admin);
        }
    }

    @RequestMapping(value = "/login/admin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> adminLogin(@Valid @RequestBody com.qiaoyansong.entity.front.User user, BindingResult result) {
        LOGGER.info("进入LoginController.adminLogin");
        List<ObjectError> allErrors = result.getAllErrors();
        ResponseEntity responseEntity = new ResponseEntity();
        if (!allErrors.isEmpty()) {
            LOGGER.error("参数校验错误，直接退出");
            List<String> msgList = new ArrayList<>();
            Iterator<ObjectError> iterator = allErrors.iterator();
            while (iterator.hasNext()) {
                msgList.add(iterator.next().getDefaultMessage());
            }
            responseEntity.setBody(msgList);
            responseEntity.setCode(StatusCode.VALID_EXCEPTION.getCode());
            return responseEntity;
        } else {
            return this.adminService.login(user);
        }
    }
}
