package com.damuzee.strategy;

import com.damuzee.model.ResultHolder;

public interface Strategy<T> {
	 public ResultHolder doOperation(T type);
}
