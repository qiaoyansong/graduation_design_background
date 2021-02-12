package com.qiaoyansong.entity.front;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/12 18:36
 * description：
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$", message = "用户名格式错误")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{9,19}$", message = "密码格式错误")
    private String password;
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String mailbox;

    @Override
    public String toString() {
        return "Admin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mailbox='" + mailbox + '\'' +
                '}';
    }
}
