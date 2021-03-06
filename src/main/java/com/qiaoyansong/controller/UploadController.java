package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.News;
import com.qiaoyansong.entity.background.ResponseEntity;
import com.qiaoyansong.entity.background.UploadFile;
import com.qiaoyansong.entity.front.UserUploadImg;
import com.qiaoyansong.service.NewsService;
import com.qiaoyansong.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/15 18:24
 * description：上传控制器
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    /**
     * 生成的文件名后缀生成3位随机数
     */
    private final int IMG_LOCATION_COUNT = 3;
    @RequestMapping("/pic")
    @ResponseBody
    public UploadFile uploadPic(@Valid @NotNull(message = "上传文件不能为空") MultipartFile file) {
        log.info("进入UploadController.pic");
        // 获取文件后缀名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        UploadFile uploadFile = new UploadFile();
        if (!extension.equals("png") && !extension.equals("jpg")) {
            log.warn("不支持的图片格式");
        } else {
            log.info("开始上传图片");
            String saveDir = "F:/graduate_design/images/upload/pic";
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
            StringBuilder base = new StringBuilder(dateTimeFormatter.format(localDateTime));
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            int i;
            for (i = 0; i < IMG_LOCATION_COUNT; i++) {
                base.append(threadLocalRandom.nextInt(0, 9));
            }
            base.append(".jpg");
            String fileName = base.toString();
            try {
                Files.copy(file.getInputStream(), Paths.get(saveDir, fileName));
                uploadFile.setFileName(fileName);
                uploadFile.setLocation(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uploadFile;
    }

    @RequestMapping(path = "/news")
    @ResponseBody
    public ResponseEntity uploadNews(@Valid @RequestBody News news, BindingResult bindingResult){
        log.info("进入UploadController.uploadNews");
        return this.newsService.uploadNews(news);
    }

    @RequestMapping("/uploadUserImg")
    @ResponseBody
    public ResponseEntity uploadUserImg(@Valid @RequestBody UserUploadImg userUploadImg, BindingResult bindingResult) {
        log.info("进入UploadController.uploadUserImg");
        return this.userService.userUploadImg(userUploadImg);
    }
}
