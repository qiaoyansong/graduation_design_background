package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.*;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.entity.front.Admin;
import com.qiaoyansong.service.AdminService;
import com.qiaoyansong.service.UserService;
import com.qiaoyansong.util.JedisPoolUtil;
import com.qiaoyansong.util.RequestContextHolderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/12 18:41
 * description：
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsMapper adminMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private AuctionMapper auctionMapper;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private Jedis redis;

    @Override
    public ResponseEntity login(com.qiaoyansong.entity.front.User admin) {
        log.info("进入AdminServiceImpl.login");
        redis = JedisPoolUtil.getInstance().getResource();
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            log.info("检测redis连接" + redis.ping());
            String mailbox = admin.getMailbox();
            boolean isExists = redis.exists(mailbox);
            log.info("开始检测验证码是否失效");
            if (isExists) {
                // 未失效
                log.info("验证码未失效");
                // 验证码设置生命周期为十秒
                redis.expire(mailbox, 10);
                // 验证验证码
                log.info("开始验证验证码");
                String redisVerificationCode = redis.get(mailbox);
                if (redisVerificationCode.equals(admin.getVerificationCode())) {
                    log.info("验证码验证成功");
                    log.info("管理员可以登陆");
                    User user = new User();
                    user.setUserName(admin.getUserName());
                    user.setPassword(admin.getPassword());
                    return userService.login(user);
                } else {
                    log.warn("验证码验证失败");
                    responseEntity.setCode(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getCode());
                    responseEntity.setBody(StatusCode.VERIFICATION_CODE_VERIFICATION_FAILED.getReason());
                }
            } else {
                // 失效了
                log.warn("验证码失效了");
                responseEntity.setBody(StatusCode.VERIFICATION_CODE_FAILURE.getReason());
                responseEntity.setCode(StatusCode.VERIFICATION_CODE_FAILURE.getCode());
            }
            return responseEntity;
        } finally {
            if (redis != null) {
                redis.close();
            }
        }
    }

    @Override
    public ResponseEntity getAdminVerificationCode(Admin admin) {
        log.info("进入AdminServiceImpl.getAdminVerificationCode");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始检测用户是否存在");
        Integer checkUserName = this.userMapper.checkUserName(admin.getUserName());
        if (checkUserName == null) {
            log.warn("当前用户名不存在，退出");
            responseEntity.setCode(StatusCode.USERNAME_IS_NOT_EXISTS.getCode());
            responseEntity.setBody(StatusCode.USERNAME_IS_NOT_EXISTS.getReason());
        } else {
            log.info("当前用户名存在");
            User user = this.userMapper.getUserInfo(admin.getUserName());
            log.info("开始检测用户权限是否足够");
            if (user.getType() != UserType.GENERAL_USER) {
                log.info("用户权限足够");
                log.info("开始检测用户密码是否正确");
                if (user.getPassword().equals(admin.getPassword())) {
                    log.info("当前用户名密码正确");
                    log.info("开始检验邮箱是否正确");
                    if (user.getMailbox().equals(admin.getMailbox())) {
                        log.info("检验邮箱正确");
                        log.info("发送验证码");
                        return this.userService.getVerificationCode(admin.getMailbox(), "管理员登录验证码");
                    } else {
                        log.warn("邮箱错误");
                        responseEntity.setCode(StatusCode.MAILBOX_ERROR.getCode());
                        responseEntity.setBody(StatusCode.MAILBOX_ERROR.getReason());
                    }
                } else {
                    log.warn("当前用户名密码不正确，退出");
                    responseEntity.setCode(StatusCode.WRONG_PASSWORD.getCode());
                    responseEntity.setBody(StatusCode.WRONG_PASSWORD.getReason());
                }
            } else {
                log.warn("用户权限不够，退出");
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadNews(News news) {
        log.info("进入AdminServiceImpl.uploadNews");
        log.info("开始验证当前session是否有用户登陆信息");
        ResponseEntity responseEntity = new ResponseEntity();
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            log.warn("当前session没有登陆过，直接退出");
            responseEntity.setBody(StatusCode.USER_IS_NOT_LOGGED_IN.getReason());
            responseEntity.setCode(StatusCode.USER_IS_NOT_LOGGED_IN.getCode());
        } else {
            log.info("当前session已经登录");
            log.info("判断是否有相关权限");
            User user = this.userMapper.getUserInfo(userName);
            if (user.getType() == UserType.GENERAL_USER) {
                log.warn("权限不足，直接退出");
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
            } else {
                log.info("开始检查文章是否重名");
                Integer flag = this.adminMapper.checkNewsTitleIsExists(news.getTitle());
                if (flag == null) {
                    log.info("开始上传文章");
                    if (this.adminMapper.uploadNews(news) != 1) {
                        log.warn("上传文章失败");
                        responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                        responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                    } else {
                        log.info("上传文章成功");
                        responseEntity.setBody(StatusCode.SUCCESS.getReason());
                        responseEntity.setCode(StatusCode.SUCCESS.getCode());
                    }
                } else {
                    log.warn("文章标题存在");
                    responseEntity.setBody(StatusCode.NEWS_TITLE_IS_EXISTS.getReason());
                    responseEntity.setCode(StatusCode.NEWS_TITLE_IS_EXISTS.getCode());
                }
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadActivity(Activity activity) {
        log.info("进入AdminServiceImpl.uploadActivity");
        log.info("开始验证当前session是否有用户登陆信息");
        ResponseEntity responseEntity = new ResponseEntity();
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            log.warn("当前session没有登陆过，直接退出");
            responseEntity.setBody(StatusCode.USER_IS_NOT_LOGGED_IN.getReason());
            responseEntity.setCode(StatusCode.USER_IS_NOT_LOGGED_IN.getCode());
        } else {
            log.info("当前session已经登录");
            log.info("判断是否有相关权限");
            User user = this.userMapper.getUserInfo(userName);
            if (user.getType() == UserType.GENERAL_USER) {
                log.warn("权限不足，直接退出");
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
            } else {
                log.info("开始上传活动");
                if (this.activityMapper.uploadActivity(activity) != 1) {
                    log.warn("上传活动失败");
                    responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                    responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                } else {
                    log.info("上传活动成功");
                    responseEntity.setBody(StatusCode.SUCCESS.getReason());
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                }
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadCommodity(Commodity commodity) {
        log.info("进入AdminServiceImpl.uploadCommodity");
        log.info("开始验证当前session是否有用户登陆信息");
        ResponseEntity responseEntity = new ResponseEntity();
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            log.warn("当前session没有登陆过，直接退出");
            responseEntity.setBody(StatusCode.USER_IS_NOT_LOGGED_IN.getReason());
            responseEntity.setCode(StatusCode.USER_IS_NOT_LOGGED_IN.getCode());
        } else {
            log.info("当前session已经登录");
            log.info("判断是否有相关权限");
            User user = this.userMapper.getUserInfo(userName);
            if (user.getType() == UserType.GENERAL_USER) {
                log.warn("权限不足，直接退出");
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
            } else {
                log.info("开始上传商品");
                if (this.commodityMapper.uploadCommodity(commodity) != 1) {
                    log.warn("上传商品失败");
                    responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                    responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                } else {
                    log.info("上传商品成功");
                    responseEntity.setBody(StatusCode.SUCCESS.getReason());
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                }
            }
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity uploadAuction(Auction auction) {
        log.info("进入AdminServiceImpl.uploadAuction");
        log.info("开始验证当前session是否有用户登陆信息");
        ResponseEntity responseEntity = new ResponseEntity();
        HttpSession session = RequestContextHolderUtil.getRequest().getSession();
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            log.warn("当前session没有登陆过，直接退出");
            responseEntity.setBody(StatusCode.USER_IS_NOT_LOGGED_IN.getReason());
            responseEntity.setCode(StatusCode.USER_IS_NOT_LOGGED_IN.getCode());
        } else {
            log.info("当前session已经登录");
            log.info("判断是否有相关权限");
            User user = this.userMapper.getUserInfo(userName);
            if (user.getType() == UserType.GENERAL_USER) {
                log.warn("权限不足，直接退出");
                responseEntity.setBody(StatusCode.INSUFFICIENT_PERMISSIONS.getReason());
                responseEntity.setCode(StatusCode.INSUFFICIENT_PERMISSIONS.getCode());
            } else {
                log.info("开始上传公益拍卖");
                if (this.auctionMapper.uploadAuction(auction) != 1) {
                    log.warn("上传公益拍卖失败");
                    responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
                    responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
                } else {
                    log.info("上传公益拍卖成功");
                    responseEntity.setBody(StatusCode.SUCCESS.getReason());
                    responseEntity.setCode(StatusCode.SUCCESS.getCode());
                }
            }
        }
        return responseEntity;
    }
}
