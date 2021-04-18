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
public class DeliverAuction {


    @NotNull(message = "拍卖ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "拍卖ID不符合格式")
    private String auctionId;

    @NotNull(message = "用户ID不能为空")
    @Pattern(regexp = "^\\d{1,}$", message = "用户ID不符合格式")
    private String userId;

}