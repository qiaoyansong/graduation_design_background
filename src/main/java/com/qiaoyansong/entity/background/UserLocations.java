package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLocations {
    private Integer id;

    private Integer userId;

    private String telephone;

    private String name;

    private Integer province;

    private Integer city;

    private Integer area;

    private Integer town;

    private String location;

}