package com.damuzee.strategy;

import com.damuzee.model.ResultHolder;
import com.damuzee.model.Task;

public interface Strategy {
	 public ResultHolder doOperation(final Task entity);
}
