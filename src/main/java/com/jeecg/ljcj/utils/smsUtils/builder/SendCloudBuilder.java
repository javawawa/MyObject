package com.jeecg.ljcj.utils.smsUtils.builder;

import com.jeecg.ljcj.utils.smsUtils.config.Config;
import com.jeecg.ljcj.utils.smsUtils.core.SendCloud;

public class SendCloudBuilder {

	public static SendCloud build() {
		SendCloud sc = new SendCloud();
		sc.setServer(Config.SERVER);
		sc.setSmsAPI(Config.SEND_SMS_API);
		return sc;
	}
}