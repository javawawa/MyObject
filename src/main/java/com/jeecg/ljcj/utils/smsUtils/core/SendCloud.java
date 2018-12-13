package com.jeecg.ljcj.utils.smsUtils.core;

import com.jeecg.ljcj.utils.smsUtils.config.Config;
import com.jeecg.ljcj.utils.smsUtils.config.Credential;
import com.jeecg.ljcj.utils.smsUtils.exception.SmsException;
import com.jeecg.ljcj.utils.smsUtils.model.SendCloudSms;
import com.jeecg.ljcj.utils.smsUtils.util.Md5Util;
import com.jeecg.ljcj.utils.smsUtils.util.ResponseData;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 
 * 发送短信代码示例
 * 
 * 
 * <p>
 * <blockquote>
 * 
 * <pre>
 * SendCloud sc = SendCloudBuilder.build();
 * 
 * SendCloudSms sms = new SendCloudSms();
 * sms.setTemplateId(65825);
 * sms.addPhone("13512345678");
 * sms.addVars("code", "123456");
 * 
 * ResponseData result = sc.sendSms(sms);
 * 
 * System.out.println(JSONObject.fromObject(result).toString());
 * </pre>
 * 
 * </blockquote>
 * <p>
 * @author SendCloud
 *
 */
public class SendCloud {

	private String server = "http://www.sendcloud.net";
	private String smsAPI;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getSmsAPI() {
		return smsAPI;
	}

	public void setSmsAPI(String smsAPI) {
		this.smsAPI = smsAPI;
	}


	/**
	 * 发送短信
	 * 
	 * @param sms
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws SmsException
	 */
	public ResponseData sendSms(SendCloudSms sms) throws ClientProtocolException, IOException, SmsException {
		Asserts.notNull(sms, "sms");
		Asserts.notBlank(Config.SMS_USER, "send.sms.user");
		Asserts.notBlank(Config.SMS_KEY, "send.sms.key");
		sms.validate();
		Credential credential = new Credential(Config.SMS_USER, Config.SMS_KEY);
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("smsUser", credential.getApiUser());
		treeMap.put("msgType", sms.getMsgType().toString());
		treeMap.put("phone", sms.getPhoneString());
		treeMap.put("templateId", sms.getTemplateId().toString());
		treeMap.put("timestamp", String.valueOf((new Date()).getTime()));
		if (MapUtils.isNotEmpty(sms.getVars())) {
			treeMap.put("vars", sms.getVarsString());
		}
		String signature = Md5Util.md5Signature(treeMap, credential.getApiKey());
		treeMap.put("signature", signature);
		Iterator<String> iterator = treeMap.keySet().iterator();
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		while (iterator.hasNext()) {
			String key = iterator.next();
			params.add(new BasicNameValuePair(key, treeMap.get(key)));
		}

		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(smsAPI);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		HttpResponse response = httpclient.execute(httpPost);
		ResponseData result = validate(response);
		httpPost.releaseConnection();
		httpclient.close();
		return result;
	}


	/**
	 * 解析返回结果
	 * 
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	private ResponseData validate(HttpResponse response) throws ParseException, IOException {
		String s = EntityUtils.toString(response.getEntity());
		ResponseData result = new ResponseData();
		if (JSONUtils.mayBeJSON(s)) {
			JSONObject json = JSONObject.fromObject(s);
			if (json.containsKey("statusCode")) {
				result.setStatusCode(json.getInt("statusCode"));
				result.setMessage(json.getString("message"));
				result.setResult(json.getBoolean("result"));
				result.setInfo(json.getJSONObject("info").toString());
			} else {
				result.setStatusCode(500);
				result.setMessage(json.toString());
			}
		} else {
			result.setStatusCode(response.getStatusLine().getStatusCode());
			result.setMessage("发送失败");
			result.setResult(false);
		}
		return result;
	}
}