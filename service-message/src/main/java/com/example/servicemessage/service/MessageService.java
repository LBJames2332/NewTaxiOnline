package com.example.servicemessage.service;


import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.servicesms.request.SmsSendRequest;

public interface MessageService {
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	public ResponseResult sendSms(SmsSendRequest request);
}