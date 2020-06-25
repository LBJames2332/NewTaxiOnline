package com.example.servicemessage.constant;

import lombok.Getter;

/**
 *  第三方短信 错误码
 */
public enum MessageStatusEnum {
	
	/**
     * 操作成功
     */
    SEND_SUCCESS(0, "message send success"),
    
    /**
     * 操作异常
     */
    INTERNAL_SERVER_EXCEPTION(-1, "exception"),
	
    /**
     * 操作失败
     */
    SEND_FAIL(1, "message send fail");
	
	@Getter
	private final int code;
	
	@Getter
    private final String value;
    
    private MessageStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}