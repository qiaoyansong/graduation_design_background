package com.qiaoyansong.entity.background;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/18 20:16
 * description：分页
 */
@Getter
@Setter
@NoArgsConstructor
public class PageHelper {
    /**
     * 总数据
     */
    private int totalSize;

    /**
     * 查询条件
     */
    @Valid
    private SearchCondition condition;

    /**
     * 当前页数
     */
    @NotNull(message = "当前页数不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "当前页数格式错误")
    private String curPage;

    /**
     * mybatis分页查询开始下标
     */
    private int beginIndex;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    public PageHelper(int totalSize, SearchCondition condition, String curPage) {
        this.totalSize = totalSize;
        this.condition = condition;
        this.curPage = curPage;
        // 开始下标为
        this.beginIndex = (Integer.parseInt(curPage) - 1) * 10;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "totalSize=" + totalSize +
                ", condition=" + condition +
                ", curPage='" + curPage + '\'' +
                ", beginIndex=" + beginIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
