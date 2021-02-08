package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuctionRealtimePrice {
    private Integer auctionId;

    private Date time;

    private Integer userId;

    private Integer price;

    @Override
    public String toString() {
        return "AuctionRealtimePrice{" +
                "auctionId=" + auctionId +
                ", time=" + time +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}