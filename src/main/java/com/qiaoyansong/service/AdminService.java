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
     * 管理员修改文章
     * @param news 文章信息
     * @return 返回信息
     */
    ResponseEntity updateNews(com.qiaoyansong.entity.front.News news);

    /**
     * 管理员删除文章
     * @param id 文章ID
     * @return 返回信息
     */
    ResponseEntity deleteNewsByID(String id);

    /**
     * 管理员删除商品
     * @param id 商品ID
     * @return 返回信息
     */
    ResponseEntity deleteCommodityByID(String id);

    /**
     * 管理员删除拍卖
     * @param id 拍卖ID
     * @return 返回信息
     */
    ResponseEntity deleteAuctionByID(String id);

    /**
     * 管理员删除活动
     * @param id 活动ID
     * @return 返回信息
     */
    ResponseEntity deleteActivityByID(String id);

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
     * 管理员修改商品信息
     * @param commodity 商品信息
     * @return 返回信息
     */
    ResponseEntity updateCommodity(com.qiaoyansong.entity.front.Commodity commodity);

    /**
     * 管理员上传拍卖商品信息
     * @param auction 卖商品信息
     * @return 返回信息
     */
    ResponseEntity uploadAuction(Auction auction);

    /**
     * 管理员获取资讯信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity selectNews(PageHelper pageHelper);

    /**
     * 管理员获取商品信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity selectCommodity(PageHelper pageHelper);

    /**
     * 管理员获取拍卖信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity selectAuction(PageHelper pageHelper);

    /**
     * 管理员获取活动信息
     * @param pageHelper 筛选 + 分页条件
     * @return 返回信息
     */
    ResponseEntity selectActivity(PageHelper pageHelper);
}
