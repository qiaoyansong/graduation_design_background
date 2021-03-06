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

    private String id;

    private String auctionId;

    private Date time;

    private String price;

    private User user;

    @Override
    public String toString() {
        return "AuctionRealtimePrice{" +
                "id='" + id + '\'' +
                ", auctionId='" + auctionId + '\'' +
                ", time=" + time +
                ", price='" + price + '\'' +
                ", user=" + user +
                '}';
    }
}