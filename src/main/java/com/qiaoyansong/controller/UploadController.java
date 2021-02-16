package com.qiaoyansong.controller;

import com.qiaoyansong.entity.background.UploadFile;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/15 18:24
 * description：上传控制器
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
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
            StringBuilder base = new StringBuilder(UUID.randomUUID().toString() + ".png");
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
}
