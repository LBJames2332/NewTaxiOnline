package com.example.apipassenger.service;


import com.example.internalcommon.dto.ResponseResult;

public interface ServiceSmsRestTemplateService {

    public ResponseResult sendSms(String phoneNumber , String code);
}
