package com.qiaoyansong.service;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.entity.background.SeekHelp;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/24 17:36
 * description：
 */
public interface SeekHelpService {

    /**
     * 用户提交求助信息
     * @param seekHelp 求助信息
     * @return 返回信息
     */
    ResponseEntity submit(SeekHelp seekHelp);

    /**
     * 同意用户提交的求助信息
     * @param id 求助ID
     * @return 返回信息
     */
    ResponseEntity agree(String id);


    /**
     * 拒绝用户提交的求助信息
     * @param id 求助ID
     * @return 返回信息
     */
    ResponseEntity refuse(String id);

    /**
     * 查看用户提交的求助信息
     * @param pageHelper 筛选信息
     * @return 返回信息
     */
    ResponseEntity listAll(PageHelper<SearchCondition> pageHelper);


    /**
     * 查看具体的求助信息
     * @param id 求助ID
     * @return 返回信息
     */
    ResponseEntity getSeekHelpInfoById(String id);

    /**
     * 查看所有审核通过的求助信息
     * @param pageHelper 筛选信息
     * @return 返回信息
     */
    ResponseEntity seekHelpList(PageHelper<SearchCondition> pageHelper);
}
