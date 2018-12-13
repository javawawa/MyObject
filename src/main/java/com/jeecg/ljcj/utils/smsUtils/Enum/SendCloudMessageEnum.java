package com.jeecg.ljcj.utils.smsUtils.Enum;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:wb-lzl282164@alibaba-inc.com">李智龙</a>
 * @since 2018/9/27
 */
public enum SendCloudMessageEnum {
    SMS_TAKE_PHONE(19996, "sms_take_phone", "验证码"),
    SMS_LEVER_BAOCANG(19993, "sms_lever_baocang", "风险告知"),
    SMS_LEVER_HONGSEYUJING(19992, "sms_lever_hongseyujing", "风险提醒");
    /**
     * 消息事件ID
     */
    private Integer messageId;
    /**
     * 数据库映射ID
     */
    private String messageCode;
    /**
     * 消息
     */
    private String message;

    SendCloudMessageEnum(Integer messageId, String messageCode, String message) {
        this.messageId = messageId;
        this.messageCode = messageCode;
        this.message = message;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据code获取当前的枚举对象
     * @param messageCode
     * @return
     */
    public static SendCloudMessageEnum of(String messageCode) {
        if (StringUtils.isEmpty(messageCode)) {
            return null;
        }
        for (SendCloudMessageEnum messageEnum : values()) {
            if (messageEnum.getMessageCode().equals(messageCode)) {
                return messageEnum;
            }
        }
        return null;
    }

}
