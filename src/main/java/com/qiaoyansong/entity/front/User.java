package com.qiaoyansong.entity.front;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/7 2042
 * description：前台来的用户信息
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{3,19}$", message = "用户名格式错误")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{9,19}$", message = "密码格式错误")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String mailbox;

    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{6}$", message = "验证码格式错误")
    private String verificationCode;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
