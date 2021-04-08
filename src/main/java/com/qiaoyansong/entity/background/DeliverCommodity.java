package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliverCommodity {

    private Integer id;

    private Integer commodityId;

    private Integer userId;

    private Integer userLocationsId;

    @Override
    public String toString() {
        return "DeliverCommodity{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", userId=" + userId +
                ", userLocationsId=" + userLocationsId +
                '}';
    }
}