package com.example.serviceuserinfo.service;


import com.example.internalcommon.dto.ResponseResult;

public interface PassengerUserService {

    public ResponseResult login(String passengerPhone);

    public ResponseResult logout(String token);
}
