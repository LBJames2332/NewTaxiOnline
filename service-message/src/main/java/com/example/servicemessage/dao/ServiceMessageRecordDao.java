package com.example.servicemessage.dao;

import com.example.servicemessage.entity.ServiceMessageRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceMessageRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceMessageRecord record);

    int insertSelective(ServiceMessageRecord record);

    ServiceMessageRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceMessageRecord record);

    int updateByPrimaryKey(ServiceMessageRecord record);
}