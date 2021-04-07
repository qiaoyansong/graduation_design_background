package com.qiaoyansong.entity.front;

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
public class AuctionRealtimePrice {

    private String id;

    @NotNull(message = "拍卖ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "拍卖ID非法")
    private String auctionId;

    @NotNull(message = "用户ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "用户ID非法")
    private String userId;

    @NotNull(message = "价格不能为空")
    @Pattern(regexp = "^\\d{1,3}$", message = "价格非法")
    private String price;

    @Override
    public String toString() {
        return "AuctionRealtimePrice{" +
                "id='" + id + '\'' +
                ", auctionId='" + auctionId + '\'' +
                ", userId='" + userId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}