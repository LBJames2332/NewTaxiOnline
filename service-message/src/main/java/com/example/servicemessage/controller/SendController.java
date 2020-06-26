package com.example.servicemessage.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.servicesms.request.MessageSendRequest;
import com.example.servicemessage.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@Slf4j
public class SendController {
	
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/message-template",method = RequestMethod.POST)
    public ResponseResult send(@RequestBody MessageSendRequest messageSendRequest){
		//输出收到的参数内容
        JSONObject param = JSONObject.fromObject(messageSendRequest);
        log.info("/send/alimessage-template   request："+param.toString());
        return messageService.sendMessage(messageSendRequest);
    }
	
}