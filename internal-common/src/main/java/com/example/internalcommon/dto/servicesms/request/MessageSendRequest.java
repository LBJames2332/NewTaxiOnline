package com.example.internalcommon.dto.servicesms.request;

import com.example.internalcommon.dto.servicesms.MessageTemplateDto;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class MessageSendRequest{

	private String[] receivers;
	private List<MessageTemplateDto> data;

	@Override
	public String toString() {
		return "SmsSendRequest [reveivers=" + Arrays.toString(receivers) + ", data=" + data + "]";
	}

}