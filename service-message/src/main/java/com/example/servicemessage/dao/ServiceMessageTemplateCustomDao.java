package com.example.servicemessage.dao;

import com.example.servicemessage.entity.ServiceMessageTemplate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceMessageTemplateCustomDao extends ServiceMessageTemplateDao {

    ServiceMessageTemplate selectByTemplateId(String templateId);
}
