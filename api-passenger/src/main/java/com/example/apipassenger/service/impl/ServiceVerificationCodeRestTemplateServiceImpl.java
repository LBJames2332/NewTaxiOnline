package com.example.apipassenger.service.impl;


import com.example.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceVerificationCodeRestTemplateServiceImpl implements ServiceVerificationCodeRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult generatorCode(int identity, String phoneNumber) {
        System.out.println("其实我到这儿了");
        String url = "http://service-verify/verify-code/generate/"+identity+"/"+phoneNumber;
        System.out.println(url);
        ResponseResult result = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<Object>(null,null),ResponseResult.class).getBody();

        return result;
    }

    @Override
    public ResponseResult verifyCode(int identity, String phoneNumber , String code) {
        System.out.println("其实我到这儿了2");
        String url = "http://service-verify/verify-code/verify/";

        VerifyCodeRequest request = new VerifyCodeRequest();
        request.setCode(code);
        request.setIdentity(identity);
        request.setPhoneNumber(phoneNumber);

        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<Object>(request,null),ResponseResult.class).getBody();

        return result;
    }
}
