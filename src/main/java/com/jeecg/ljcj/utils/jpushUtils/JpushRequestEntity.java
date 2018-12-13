package com.jeecg.ljcj.utils.jpushUtils;


public class JpushRequestEntity {
    //推送类型 1 全退 0 单推
    private String pushType;

    //如果是单推 需要推送的id 后台需要传数组
    private String[] registrationId;

    //推送弹出消息
    private String alert;

    //extras字段中的参数 传给前端 1 内容  2 活动 3 快讯 4公告 5作者审核 6.评论 暂定
    private String type;

    //extras中的字段 一般为文章、快讯的id
    private String id;

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String[] getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String[] registrationId) {
        this.registrationId = registrationId;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
