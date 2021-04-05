package com.qiaoyansong.entity.background;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserActivity {

    private String id;

    @NotNull(message = "用户ID不能为空")
    private String userId;

    @NotNull(message = "活动ID不能为空")
    private String activityId;

    private Byte progress;

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", userId=" + userId +
                ", activityId=" + activityId +
                ", progress=" + progress +
                '}';
    }
}