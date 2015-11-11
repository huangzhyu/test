package com.damuzee.model;

public class Member {
	private String userId;
	
	private String invitedCode;
	
	private String orderId;
	
	private String taskId;
	
	private String creater;
	
	
	

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInviteCode() {
		return invitedCode;
	}

	public void setInviteCode(String invitedCode) {
		this.invitedCode = invitedCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
