package com.damuzee.db;

import java.util.Map;

import org.damuzee.mongo.SimpleMapObject;

import com.damuzee.common.Utils;
import com.damuzee.model.Member;

public class MemberAccessImpl extends DataAccessAdapter<Member> {
	

	@Override
	public Member getFirst(Member member) {
		//通过orderId在payOrder表中找taskId
		String taskId = findResult(member.getOrderId(), "orderNO", "payOrder", "taskID");
		if(Utils.isNull(taskId)) return null;
		member.setTaskId(taskId);
		
		//通过taskId在task表中找任务发布者的id（creater）
		String creater = findResult(member.getTaskId(), "_id", "task", "creater");
		if(Utils.isNull(creater)) return null;
		
//		通过creater在member表中找到对应的member
		
		Map<String, Object> userMap = find(member.getCreater(), "telephone", "member", "_id");
		
		if(Utils.isNull(userMap)) return null;
		
		Object userId = userMap.get("_id");
		if(Utils.isNull(userId)) return null;
		member.setUserId(userId.toString());
		Object invitedCode = userMap.get("invitedCode");
		if(!Utils.isNull(invitedCode)) member.setInviteCode(invitedCode.toString());
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
        sm.put(conditionFieldName,conditionValue);
		Map<String, Object> map = getMongoTemplate().findOne(tableName, sm);
		return map;
	}
}
