package com.jeecg.ljcj.utils.smsUtils.util;

import net.sf.json.JSONObject;

public class ResponseData {

	public boolean result;
	/**
	 * 请求成功200
	 * 手机号格式错误412
	 * 部分号码请求成功311
	 */
	public int statusCode;
	/**
	 * 成功or错误信息
	 */
	public String message;
	/**
	 * 详细信息
	 */
	public String info;

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}