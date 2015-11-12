package com.damuzee.model;

import java.math.BigDecimal;

import com.damuzee.common.Operation;

public class Member {
	private String userId;
	
	private String invitedCode;
	
	private String orderId;
	
	private String taskId;
	
	private String creater;
	
	private String invite;
	
	private int ratio;
	
	private BigDecimal amount;
	
	private byte operationType;
	
	public Member(String orderId) {
        this.orderId = orderId;
    }
	
	public Member(){}
	
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }
    
    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        try {
            BigDecimal bd = new BigDecimal(amount);
            this.amount = bd;
        } catch (NumberFormatException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public byte getOperationType() {
        return operationType;
    }

    public void setOperationType(Operation type) {
        if(Operation.INCOME.equals(type)){
            this.operationType=0;
        }else if(Operation.PAY.equals(type)){
            this.operationType = 1;
        }else{
            throw new IllegalStateException("Only [Operation.PAY] or [Operation.INCOME] supproted.");
        }
    }

    public void setOperationType(byte operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "Member [userId=" + userId + ", invitedCode=" + invitedCode + ", orderId=" + orderId + ", taskId=" + taskId + ", creater=" + creater + ", invite=" + invite + ", ratio=" + ratio
                + ", amount=" + amount + ", operationType=" + operationType + "]";
    }


}
