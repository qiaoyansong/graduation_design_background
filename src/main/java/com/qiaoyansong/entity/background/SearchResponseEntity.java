package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/18 20:57
 * description：
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResponseEntity extends ResponseEntity{
    private int totalSize;

    @Override
    public String toString() {
        return "SearchResponseEntity{" +
                "totalSize=" + totalSize +
                '}';
    }
}
