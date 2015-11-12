package com.damuzee.strategy.impl;

import java.util.List;

import com.damuzee.db.DataAccess;
import com.damuzee.model.Integral;
import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.Strategy;

public class CheckoutStrategy implements Strategy<ResultHolder> {
	private DataAccess<ResultHolder> dataAccess;
	
    public DataAccess<ResultHolder> getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess<ResultHolder> dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
    public ResultHolder doOperation(ResultHolder holder) {
        dataAccess.add(holder);
        return null;
    }


}
