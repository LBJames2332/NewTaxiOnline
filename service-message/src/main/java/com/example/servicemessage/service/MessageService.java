package com.example.servicemessage.service;


import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.servicesms.request.MessageSendRequest;

public interface MessageService {
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	public ResponseResult sendMessage(MessageSendRequest request);
}