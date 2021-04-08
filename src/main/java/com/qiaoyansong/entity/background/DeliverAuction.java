package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliverAuction {

    private Integer id;

    private Integer auctionId;

    private Integer userId;

    private Integer userLocationsId;

    @Override
    public String toString() {
        return "DeliverAuction{" +
                "id=" + id +
                ", auctionId=" + auctionId +
                ", userId=" + userId +
                ", userLocationsId=" + userLocationsId +
                '}';
    }
}