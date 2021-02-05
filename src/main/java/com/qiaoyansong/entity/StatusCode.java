package com.qiaoyansong.entity;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/2 18:53
 * description：
 */
public enum StatusCode {
    /**
     * 邮件发送失败
     */
    SEND_EMAIL_FAILED("邮件发送失败","101"),
    /**
     * 一切正常
     */
    COMPLETE("业务逻辑正常","200"),
    /**
     * 发生请求参数校验失败异常
     */
    VALID_EXCEPTION("发生请求参数校验失败异常","102");
    private final String reason;
    private final String code;
    /**
     * @param reason 状态码原因
     * @param code 状态码
     */
    private StatusCode(String reason, String code){
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
