package com.example.apipassenger.service.impl;


import com.example.apipassenger.service.ServiceMessageRestTemplateService;
import com.example.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.example.apipassenger.service.VerificationCodeService;
import com.example.internalcommon.constant.CommonStatusEnum;
import com.example.internalcommon.constant.IdentityConstant;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.netflix.discovery.converters.Auto;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;

    @Autowired
    private ServiceMessageRestTemplateService serviceMessageRestTemplateService;

    @Override
    public ResponseResult send(String phoneNumber) {
        // 获取验证码
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.generatorCode(IdentityConstant.PASSENGER,phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if (responseResult.getCode() == CommonStatusEnum.SUCCESS.getCode()){
            JSONObject data = JSONObject.fromObject(responseResult.getData().toString());
            verifyCodeResponse = (VerifyCodeResponse)JSONObject.toBean(data,VerifyCodeResponse.class);

        }else {
            return ResponseResult.fail("获取验证码失败");
        }

        String code = verifyCodeResponse.getCode();
        ResponseResult result = serviceMessageRestTemplateService.sendMessage(phoneNumber,code);
        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResult.fail("发送短信 失败");
        }

        return ResponseResult.success("");

    }

    @Override
    public ResponseResult verify(String phoneNumber, String code) {

        return serviceVerificationCodeRestTemplateService.verifyCode(IdentityConstant.PASSENGER,phoneNumber,code);
    }

}
