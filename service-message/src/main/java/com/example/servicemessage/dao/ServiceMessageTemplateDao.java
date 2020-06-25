package com.example.servicemessage.dao;

import com.example.servicemessage.entity.ServiceMessageTemplate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceMessageTemplateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceMessageTemplate record);

    int insertSelective(ServiceMessageTemplate record);

    ServiceMessageTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceMessageTemplate record);

    int updateByPrimaryKey(ServiceMessageTemplate record);
}