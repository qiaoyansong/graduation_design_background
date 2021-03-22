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
public class UserLocations {
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    private String userId;

    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "电话号码不符合格式")
    private String telephone;

    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "收件人姓名不符合格式")
    private String name;

    @NotNull(message = "省不能为空")
    private String province;

    @NotNull(message = "市不能为空")
    private String city;

    @NotNull(message = "区不能为空")
    private String area;

    @Pattern(regexp = "^.{0,100}$", message = "详细地址不符合格式")
    private String location;

    @Override
    public String toString() {
        return "UserLocations{" +
                "id=" + id +
                ", userId=" + userId +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}