package com.jeecg.ljcj.utils.smsUtils.model;

import com.jeecg.ljcj.utils.smsUtils.config.Config;
import com.jeecg.ljcj.utils.smsUtils.exception.SmsException;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.util.Asserts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendCloudSms {
	/**
	 * 模板id
	 */
	public Integer templateId;
	/**
	 * 0表示短信, 1表示彩信,2表示国际短信， 默认值为0
	 */
	public Integer msgType = 0;
	/**
	 * 收信人手机号,多个手机号用逗号,分隔，每次调用最大支持2000，更多地址建议使用联系人列表功能
	 */
	public List<String> phone;
	/**
	 * 替换变量的json串
	 */
	public Map<String, String> vars;

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public List<String> getPhone() {
		return phone;
	}

	public String getPhoneString() {
		Asserts.notNull(phone, "phone");
		StringBuilder sb = new StringBuilder();
		for (String p : phone) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(p);
		}
		return sb.toString();
	}

	public Map<String, String> getVars() {
		return vars;
	}

	public String getVarsString() {
		Asserts.notNull(vars, "vars");
		return JSONObject.fromObject(vars).toString();
	}

	public void addPhone(String mobile) {
		if (CollectionUtils.isEmpty(phone)) {
			phone = new ArrayList<String>();
		}
		phone.add(mobile);
	}

	public void addVars(String key, String value) {
		if (MapUtils.isEmpty(vars)) {
			vars = new HashMap<String, String>();
		}
		vars.put(key, value);
	}
    public void addVars(Map<String,String> params) {
        if (MapUtils.isEmpty(vars)) {
            vars = new HashMap<String, String>();
        }
        vars.putAll(params);
    }
	public boolean validate() throws SmsException {
		if (templateId == null) {
			throw new SmsException("模版为空");
		}
		if (CollectionUtils.isEmpty(phone)) {
			throw new SmsException("收信人为空");
		}
		if (phone.size() > Config.MAX_RECEIVERS) {
			throw new SmsException("收信人超过限制");
		}
		return true;
	}
}