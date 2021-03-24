package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.PageHelper;
import com.qiaoyansong.entity.background.SearchCondition;
import com.qiaoyansong.entity.background.SeekHelp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/24 17:46
 * description：
 */
@Mapper
public interface SeekHelpMapper {

    /**
     * 用户上传求助信息
     * @param seekHelp 求助信息
     * @return
     */
    Integer submit(SeekHelp seekHelp);


    /**
     * 同意用户的求助信息
     * @param id 求助ID
     * @return
     */
    Integer agree(String id);

    /**
     * 拒绝用户的求助信息
     * @param id 求助ID
     * @return
     */
    Integer refuse(String id);

    /**
     * 根据查询条件查询数据总条数
     * @param condition 查询条件
     * @return
     */
    Integer getTotalSize(SearchCondition condition);

    /**
     * 根据查询条件查询数据总条数
     * @param pageHelper 分页条件
     * @return
     */
    List<SeekHelp> listAll(PageHelper pageHelper);

    /**
     * 查看具体的的求助信息
     * @param id 求助ID
     * @return
     */
    SeekHelp getSeekHelpInfoById(String id);
}
