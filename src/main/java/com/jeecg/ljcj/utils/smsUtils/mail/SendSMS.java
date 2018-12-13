package com.jeecg.ljcj.utils.smsUtils.mail;


import com.jeecg.ljcj.utils.smsUtils.builder.SendCloudBuilder;
import com.jeecg.ljcj.utils.smsUtils.core.SendCloud;
import com.jeecg.ljcj.utils.smsUtils.exception.SmsException;
import com.jeecg.ljcj.utils.smsUtils.model.SendCloudSms;
import com.jeecg.ljcj.utils.smsUtils.util.ResponseData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SendSMS {
    public static ResponseData send(int templateId, String mobile, Map<String, String> params) throws IOException, SmsException {
        SendCloudSms sms = new SendCloudSms();
        sms.setMsgType(0);
        sms.setTemplateId(templateId);
        sms.addPhone(mobile);
        sms.addVars(params);
        SendCloud sc = SendCloudBuilder.build();
        return sc.sendSms(sms);
    }

    public static void main(String[] args) throws Throwable {
        Map<String, String> params = new HashMap<String, String>();
        params.put("source","币世界");
        ResponseData res = send(21021, "17719774973", params);
        System.out.println(res.getResult());
        System.out.println(res.getStatusCode());
        System.out.println(res.getMessage());
        System.out.println(res.getInfo());
    }
}
