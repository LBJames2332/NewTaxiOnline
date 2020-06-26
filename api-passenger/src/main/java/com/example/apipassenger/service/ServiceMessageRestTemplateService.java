package com.example.apipassenger.service;


import com.example.internalcommon.dto.ResponseResult;

public interface ServiceMessageRestTemplateService {

    public ResponseResult sendMessage(String phoneNumber , String code);
}
