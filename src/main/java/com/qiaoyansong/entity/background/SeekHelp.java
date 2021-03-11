package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeekHelp {

    private Integer id;

    private Integer userId;

    private Byte flag;

    private String content;

    @Override
    public String toString() {
        return "SeekHelp{" +
                "id=" + id +
                ", userId=" + userId +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                '}';
    }
}