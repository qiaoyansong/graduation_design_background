package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/15 18:46
 * description：
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件地址
     */
    private String location;

    @Override
    public String toString() {
        return "UploadFile{" +
                "fileName='" + fileName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
