package com.example.apipassenger.controller;

import com.example.apipassenger.request.ShortMsgRequest;
import com.example.apipassenger.service.VerificationCodeService;
import com.example.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify-code")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public ResponseResult send(@RequestBody @Validated ShortMsgRequest request){
        return verificationCodeService.send(request.getPhoneNumber());
    }
    @GetMapping("/test")
    public void test(){
        System.out.println("可以吗?");
    }
}
