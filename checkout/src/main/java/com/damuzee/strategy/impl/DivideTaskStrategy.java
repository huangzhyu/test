package com.damuzee.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.db.TaskDAO;
import com.damuzee.model.ResultHolder;
import com.damuzee.model.Task;
import com.damuzee.strategy.Strategy;

/**
 * 此类用来分解Task，获取当前Task的前两级Task（若存在），并计算出每级Task应得的返利
 * @author hadoop
 *
 */
public class DivideTaskStrategy implements Strategy {
	private static  final Logger logger = LoggerFactory.getLogger(DivideTaskStrategy.class);
	@Override
	public ResultHolder doOperation(final Task entity) {
		logger.info("start to divide task:"+entity);
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(entity);
		TaskDAO dao = new TaskDAO(); 
		final Task secondLevelTask = dao.getSuperTask(entity);
		if(secondLevelTask!=null){
			logger.info("The parent task of "+entity+" is:"+secondLevelTask);
			tasks.add(secondLevelTask);
			final Task firstLevelTask = dao.getSuperTask(secondLevelTask);
			if(firstLevelTask!=null){
				logger.info("The parent task of "+secondLevelTask+" is:"+firstLevelTask);
				tasks.add(firstLevelTask);
			}
		}else{
			logger.info("There is no parent task exist for "+entity);
		}
		
		ResultHolder holder = new ResultHolder();
		holder.setTasks(tasks);
		System.out.println(holder.getTasks());
		return holder;
	}

}
