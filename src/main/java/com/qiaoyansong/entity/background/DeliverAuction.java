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
public class DeliverAuction {

    private Integer id;

    @NotNull(message = "拍卖ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "拍卖ID不符合格式")
    private String auctionId;

    @NotNull(message = "用户ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "用户ID不符合格式")
    private String userId;

    @NotNull(message = "地址ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "地址ID不符合格式")
    private String userLocationsId;

    private Auction auction;
    private UserLocations userLocations;

    private Byte flag;

    @Override
    public String toString() {
        return "DeliverAuction{" +
                "id=" + id +
                ", auctionId='" + auctionId + '\'' +
                ", userId='" + userId + '\'' +
                ", userLocationsId='" + userLocationsId + '\'' +
                ", auction=" + auction +
                ", userLocations=" + userLocations +
                ", flag=" + flag +
                '}';
    }
}