package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.entity.front.Admin;
import com.qiaoyansong.entity.front.User;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/12 18:40
 * description：管理员
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param admin 管理员信息
     * @return 返回信息
     */
    ResponseEntity login(User admin);

    /**
     * 管理员登录获取验证码
     * @param admin 管理员信息
     * @return 返回信息
     */
    ResponseEntity getAdminVerificationCode(Admin admin);

    /**
     * 管理员上传文章
     * @param news 文章信息
     * @return 返回信息
     */
    ResponseEntity uploadNews(News news);

    /**
     * 管理员上传活动
     * @param activity 活动信息
     * @return 返回信息
     */
    ResponseEntity uploadActivity(Activity activity);

    /**
     * 管理员上传商品信息
     * @param commodity 商品信息
     * @return 返回信息
     */
    ResponseEntity uploadCommodity(Commodity commodity );
    /**
     * 管理员上传拍卖商品信息
     * @param auction 卖商品信息
     * @return 返回信息
     */
    ResponseEntity uploadAuction(Auction auction);
}
