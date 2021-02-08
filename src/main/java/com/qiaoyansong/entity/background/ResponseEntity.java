package com.qiaoyansong.entity.background;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/5 19:39
 * description：请求响应实例
 */

@Getter
@Setter
@NoArgsConstructor
public class ResponseEntity<T> {
    T body;
    transient  String code;

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "body=" + body +
                ", code=" + code +
                '}';
    }
}
