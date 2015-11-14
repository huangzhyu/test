package com.damuzee.db;

import com.damuzee.model.ResultHolder;

public abstract class AbstractIntegralAccess extends DataAccessAdapter<ResultHolder> {
	/**
	 * 查询指定用户可用的积分总数
	 * @param holder
	 * @return
	 */
	public abstract long getSumIntegral(ResultHolder holder);
}
