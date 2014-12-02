package com.example.testpulltofresh;


public class ScheduleData {
	private String imageUrl;
	private String userName;
	private String sendTime;
	private String scheduleContent;
	private String remindDate;
	private String attentionAmount;
	private String recordAmount;
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getScheduleContent() {
		return scheduleContent;
	}
	public void setScheduleContent(String scheduleContent) {
		this.scheduleContent = scheduleContent;
	}
	public String getRemindDate() {
		return remindDate;
	}
	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
	public String getAttentionAmount() {
		return attentionAmount;
	}
	public void setAttentionAmount(String attentionAmount) {
		this.attentionAmount = attentionAmount;
	}
	public String getRecordAmount() {
		return recordAmount;
	}
	public void setRecordAmount(String recordAmount) {
		this.recordAmount = recordAmount;
	}
	
	public ScheduleData getScheduleData() {
			return this;
	}
}
