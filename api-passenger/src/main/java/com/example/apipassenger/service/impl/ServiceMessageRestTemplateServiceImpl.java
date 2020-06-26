package com.example.apipassenger.service.impl;


import com.example.apipassenger.service.ServiceMessageRestTemplateService;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.servicesms.MessageTemplateDto;
import com.example.internalcommon.dto.servicesms.request.MessageSendRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceMessageRestTemplateServiceImpl implements ServiceMessageRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult sendMessage(String phoneNumber , String code) {

        String http = "http://";
        String serviceName = "service-message";
        String uri = "/send/message-template";

        String url = http + serviceName + uri;
        MessageSendRequest messageSendRequest = new MessageSendRequest();
        String[] phoneNumbers = new String[] {phoneNumber};
        messageSendRequest.setReceivers(phoneNumbers);

        List<MessageTemplateDto> data = new ArrayList<MessageTemplateDto>();
        MessageTemplateDto dto = new MessageTemplateDto();
        dto.setId("SMS_144145499");
        int templateSize = 1;
        HashMap<String, Object> templateMap = new HashMap<String, Object>(templateSize);
        templateMap.put("code", code);
        dto.setTemplateMap(templateMap);
        data.add(dto);

        messageSendRequest.setData(data);

        return restTemplate.postForEntity(url, messageSendRequest, ResponseResult.class).getBody();
    }
}
