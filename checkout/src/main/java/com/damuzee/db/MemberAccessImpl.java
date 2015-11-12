package com.damuzee.db;

import java.util.Map;

import org.bson.types.ObjectId;
import org.damuzee.mongo.MongoConstaints;
import org.damuzee.mongo.SimpleMapObject;

import com.damuzee.common.Constant;
import com.damuzee.common.Utils;
import com.damuzee.model.Member;

public class MemberAccessImpl extends AbstractMemberAccess {

    @Override
    public Member getSuperiorMember(Member member) {
        //查询含有指定邀请码的member
        Map<String, Object> resultMap = this.find(member.getInvitedCode(), Constant.INVITE, Constant.MEMBER, MongoConstaints.ID);
        Member result = new Member(member.getOrderId());
        result.setOperationType(member.getOperationType());
        if (Utils.isNull(resultMap))
            return null;
        Object userId = resultMap.get(MongoConstaints.ID);
        if (Utils.isNull(userId))
            return null;
        result.setUserId(userId.toString());
        Object invitedCode = resultMap.get(Constant.INVITED_CODE);
        if (!Utils.isNull(invitedCode))
            result.setInvitedCode(invitedCode.toString());
        Object invite = resultMap.get(Constant.INVITE);
        if(!Utils.isNull(invite))
            result.setInvite(invite.toString());
        return result;
    }
	

	@Override
	public Member getFirst(Member member) {
		//通过orderId在payOrder表中找taskId
		Map<String, Object> payOrder = find(member.getOrderId(),Constant.ORDER_NO, Constant.PAY_ORDER_TABLE, Constant.TASK_ID);
		Object taskId = payOrder.get(Constant.TASK_ID);
		if(Utils.isNull(taskId)) return null;
		member.setTaskId(taskId.toString());
		Object amount = payOrder.get(Constant.AMOUNT);
		if(Utils.isNull(amount)) return null;
		//判断金额是不是负数或者0
		if(amount.toString().startsWith("-") || amount.toString().equals("0")){
		    return null;
		}
		try{
		    member.setAmount(amount.toString());
		}catch(IllegalStateException e){
		    return null;
		}
		
		//通过taskId在task表中找任务发布者的id（creater）
		String creater = findResult(member.getTaskId(), MongoConstaints.ID, Constant.TASK, Constant.CREATER);
		if(Utils.isNull(creater)) return null;
		member.setCreater(creater);
		
//		通过creater在member表中找到对应的member
		
		Map<String, Object> userMap = find(member.getCreater(), Constant.TELEPHONE, Constant.MEMBER, MongoConstaints.ID);
		
		if(Utils.isNull(userMap)) return null;
		
		Object userId = userMap.get(MongoConstaints.ID);
		if(Utils.isNull(userId)) return null;
		member.setUserId(userId.toString());
		Object invitedCode = userMap.get(Constant.INVITED_CODE);
		if(!Utils.isNull(invitedCode)) member.setInvitedCode(invitedCode.toString());
		return member;
	}
	
	/**
	 * 
	 * @param conditionValue：查询条件的值
	 * @param conditionFieldName:查询条件对应的数据库中的字段名
	 * @param tableName：表名
	 * @param resultFieldName：查询结果对应的数据库中的字段名
	 * @return
	 */
	private String findResult(String conditionValue,String conditionFieldName,String tableName,String resultFieldName){
		Map<String, Object> map = find(conditionValue, conditionFieldName, tableName, resultFieldName);
		if (Utils.isNull(map)) return null;
		Object taskId = map.get(resultFieldName);
		if(Utils.isNull(taskId)) return null;
		return taskId.toString();
	}
	
	private Map<String,Object> find(String conditionValue,String conditionFieldName,String tableName,String resultFieldName){
		SimpleMapObject sm = new SimpleMapObject();
        if(MongoConstaints.ID.equals(conditionFieldName)){
            sm.put(MongoConstaints.ID, new ObjectId(conditionValue));
        }else{
            sm.put(conditionFieldName,conditionValue);
        }
		Map<String, Object> map = getMongoTemplate().findOne(tableName, sm);
		return map;
	}
}
