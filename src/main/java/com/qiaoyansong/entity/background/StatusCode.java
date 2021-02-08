package com.qiaoyansong.entity.background;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/2 18:53
 * description：
 */
public enum StatusCode {
    /**
     * 发生请求参数校验失败异常
     */
    VALID_EXCEPTION("发生请求参数校验失败异常", "100"),
    /**
     * 邮件发送失败
     */
    SEND_EMAIL_FAILED("邮件发送失败", "101"),
    /**
     * 邮件发送成功
     */
    SEND_EMAIL_SUCCESS("邮件发送成功", "102"),
    /**
     * 生成验证码成功
     */
    CREATE_VERIFICATION_CODE_COMPLETE("生成验证码成功", "103"),
    /**
     * 验证码未失效，不再生成新的验证码
     */
    VERIFICATION_CODE_NOT_EXPIRED("验证码没有失效，不再生成新的验证码", "104"),
    /**
     * 验证码失效，需要用户重新申请验证码
     */
    VERIFICATION_CODE_FAILURE("验证码失效，需要用户重新申请验证码", "105"),
    /**
     * 验证码验证失败，请到邮箱查看验证码
     */
    VERIFICATION_CODE_VERIFICATION_FAILED("验证码验证失败，请到邮箱查看验证码", "106"),
    /**
     * 用户名已经存在
     */
    USERNAME_IS_EXISTS("用户名已经存在", "107"),
    /**
     * 邮箱已经存在
     */
    MAILBOX_IS_EXISTS("邮箱已经存在", "108"),
    /**
     * 系统发生未知错误
     */
    UNKNOWN_ERROR("系统发生未知错误", "109"),
    /**
     * 成功
     */
    SUCCESS("成功", "200");
    private final String reason;
    private final String code;

    /**
     * @param reason 状态码原因
     * @param code   状态码
     */
    private StatusCode(String reason, String code) {
        this.reason = reason;
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "reason='" + reason + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
