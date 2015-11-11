package com.damuzee.strategy.impl;

import com.damuzee.model.ResultHolder;
import com.damuzee.model.Task;
import com.damuzee.strategy.Strategy;

public class CheckoutStrategy implements Strategy {

	@Override
	public ResultHolder doOperation(final Task task) {
		System.out.println("CheckoutStrategy "+task);
		
//		获取task的最新数据
//		将更新过程记录到日志，以便出现故障后恢复
//		执行更新操作
		
		return new ResultHolder();
	}


}
