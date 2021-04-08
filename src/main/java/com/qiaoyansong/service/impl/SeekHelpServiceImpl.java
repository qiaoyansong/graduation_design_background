package com.qiaoyansong.service.impl;

import com.qiaoyansong.dao.SeekHelpMapper;
import com.qiaoyansong.entity.background.*;
import com.qiaoyansong.service.SeekHelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/3/24 17:39
 * description：
 */
@Service
public class SeekHelpServiceImpl implements SeekHelpService {

    @Autowired
    private SeekHelpMapper seekHelpMapper;
    private static final Logger log = LoggerFactory.getLogger(SeekHelpServiceImpl.class);


    @Override
    public ResponseEntity submit(SeekHelp seekHelp) {
        log.info("进入SeekHelpServiceImpl的submit方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始上传求助信息");
        if (this.seekHelpMapper.submit(seekHelp) != 1) {
            log.warn("上传求助信息失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("上传求助信息成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity agree(String id) {
        log.info("进入SeekHelpServiceImpl的agree方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("同意用户的求助信息");
        if (this.seekHelpMapper.agree(id) != 1) {
            log.warn("同意用户求助信息操作失败");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("同意用户求助信息操作成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity refuse(String id) {
        log.info("进入SeekHelpServiceImpl的refuse方法");
        ResponseEntity responseEntity = new ResponseEntity();
        log.info("开始拒绝用户的求助信息");
        if (this.seekHelpMapper.refuse(id) != 1) {
            log.warn("拒绝用户求助信息操作成功");
            responseEntity.setBody(StatusCode.UNKNOWN_ERROR.getReason());
            responseEntity.setCode(StatusCode.UNKNOWN_ERROR.getCode());
        } else {
            log.info("拒绝用户求助信息操作成功");
            responseEntity.setBody(StatusCode.SUCCESS.getReason());
            responseEntity.setCode(StatusCode.SUCCESS.getCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity listAll(PageHelper<SearchCondition> pageHelper) {
        log.info("进入SeekHelpServiceImpl的refuse方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.seekHelpMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<SeekHelp> news = this.seekHelpMapper.listAll(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }

    @Override
    public ResponseEntity getSeekHelpInfoById(String id) {
        log.info("进入SeekHelpServiceImpl的getSeekHelpInfoById方法");
        ResponseEntity responseEntity = new ResponseEntity();
        SeekHelp news = this.seekHelpMapper.getSeekHelpInfoById(id);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        return responseEntity;
    }

    @Override
    public ResponseEntity seekHelpList(PageHelper<SearchCondition> pageHelper) {
        log.info("进入SeekHelpServiceImpl的seekHelpList方法");
        SearchResponseEntity responseEntity = new SearchResponseEntity();
        int totalSize = this.seekHelpMapper.getTotalSize(pageHelper.getCondition());
        PageHelper cur = new PageHelper(totalSize, pageHelper.getCondition(), pageHelper.getCurPage());
        List<SeekHelp> news = this.seekHelpMapper.listAll(cur);
        responseEntity.setCode(StatusCode.SUCCESS.getCode());
        responseEntity.setBody(news);
        responseEntity.setTotalSize(totalSize);
        return responseEntity;
    }
}
