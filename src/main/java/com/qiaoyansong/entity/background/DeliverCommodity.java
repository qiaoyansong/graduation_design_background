package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliverCommodity {

    private String id;

    @NotNull(message = "商品ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "商品ID不符合格式")
    private String commodityId;

    @NotNull(message = "用户ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "用户ID不符合格式")
    private String userId;

    @NotNull(message = "地址ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "地址ID不符合格式")
    private String userLocationsId;

    private Commodity commodity;
    private UserLocations userLocations;

    private Byte flag;

    @Override
    public String toString() {
        return "DeliverCommodity{" +
                "id='" + id + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", userId='" + userId + '\'' +
                ", userLocationsId='" + userLocationsId + '\'' +
                ", commodity=" + commodity +
                ", userLocations=" + userLocations +
                ", flag=" + flag +
                '}';
    }
}