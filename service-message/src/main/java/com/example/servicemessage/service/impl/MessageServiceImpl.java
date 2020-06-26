package com.example.servicemessage.service.impl;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.servicesms.MessageTemplateDto;
import com.example.internalcommon.dto.servicesms.request.MessageSendRequest;
import com.example.servicemessage.constant.MessageStatusEnum;
import com.example.servicemessage.dao.ServiceMessageRecordDao;
import com.example.servicemessage.dao.ServiceMessageTemplateCustomDao;
import com.example.servicemessage.entity.ServiceMessageRecord;
import com.example.servicemessage.entity.ServiceMessageTemplate;
import com.example.servicemessage.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    /**
     *   缓存用于替换内容的模板
     */
    private Map<String, String> templateMaps = new HashMap<String, String>();

    @Autowired
    private ServiceMessageTemplateCustomDao serviceMessageTemplateCustomDao;

    @Autowired
    private ServiceMessageRecordDao serviceMessageRecordDao;

    @Override
    public ResponseResult sendMessage(MessageSendRequest request) {
        log.info(request.toString());

        for (String phoneNumber : request.getReceivers()) {
            List<MessageTemplateDto> templates = request.getData();

            ServiceMessageRecord sms = new ServiceMessageRecord();
            sms.setPhoneNumber(phoneNumber);
            // 81B*10 1024 1K 10*1024  1M
            for (MessageTemplateDto template : templates) {
                // 从DB加载模板内容至缓存
                if (!templateMaps.containsKey(template.getId())) {
                    //此处注释掉的内容为，将db模板加载到内存
                    ServiceMessageTemplate t = serviceMessageTemplateCustomDao.selectByTemplateId(template.getId());
                    System.out.println(t.getTemplateContent());
                    templateMaps.put(template.getId(),
                            serviceMessageTemplateCustomDao.selectByTemplateId(template.getId()).getTemplateContent());
                }

                // 替换占位符
                String content = templateMaps.get(template.getId());
                for (Map.Entry<String, Object> entry : template.getTemplateMap().entrySet()) {
                    content = StringUtils.replace(content, "${" + entry.getKey() + "}", "" + entry.getValue());
                }

                // 发生错误时，不影响其他手机号和模板的发送
                try {
                    int result = send(phoneNumber, template.getId(), template.getTemplateMap());

                    // 组装SMS对象
                    sms.setSendTime(new Date());
                    sms.setOperatorName("");
                    sms.setSendFlag(1);
                    sms.setSendNumber(0);
                    sms.setSmsContent(content);

                    if (result != MessageStatusEnum.SEND_SUCCESS.getCode()) {
                        throw new Exception("短信发送失败");
                    }
                } catch (Exception e) {
                    sms.setSendFlag(0);
                    sms.setSendNumber(1);
                    log.error("发送短信（" + template.getId() + "）失败：" + phoneNumber, e);
                } finally {
                    sms.setCreateTime(new Date());
                    serviceMessageRecordDao.insert(sms);
                }
            }
        }
        return ResponseResult.success("");
    }

    private int send(String phoneNumber, String templateId, Map<String, ?> map) throws Exception {
        JSONObject param = new JSONObject();
        param.putAll(map);

        return sendMsg(phoneNumber, templateId, param.toString());
    }

    private int sendMsg(String phoneNumber, String templateCode, String param) {

        /**
         *  供应商 发 短信
         */
        return MessageStatusEnum.SEND_SUCCESS.getCode();

    }
}
