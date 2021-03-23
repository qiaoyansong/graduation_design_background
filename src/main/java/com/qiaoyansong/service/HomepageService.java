package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.ResponseEntity;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/23 18:23
 * description：
 */
public interface HomepageService {

    /**
     * 获取首页的国内资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageGuoNei();

    /**
     * 获取首页的政府资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageZhengFu();

    /**
     * 获取首页的学校资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageXueXiao();

    /**
     * 获取首页的企业资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageQiYe();

    /**
     * 获取首页的益人资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageYiRen();

    /**
     * 获取首页的好人资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageHaoRen();

    /**
     * 获取首页的好事资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepageHaoShi();

    /**
     * 获取首页的评选资讯展示
     * @return 资讯信息
     */
    ResponseEntity getListOfHomepagePingXuan();
}
