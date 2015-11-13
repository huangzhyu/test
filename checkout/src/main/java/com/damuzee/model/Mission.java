package com.damuzee.model;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.strategy.StrategyContext;

public class Mission<T> implements Callable<ResultHolder> ,Runnable{
    private static  final Logger logger = LoggerFactory.getLogger(Mission.class);
	private StrategyContext<T> context;
	private T entity;
	
	public Mission(StrategyContext<T> context,T entity) {
		this.context = context;
		this.entity = entity;
	}
	
	public void setContext(StrategyContext<T> context) {
		this.context = context;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public StrategyContext<T> getContext() {
        return context;
    }

    public T getEntity() {
        return entity;
    }

    @Override
	public ResultHolder call() throws Exception {
		return context.executeStrategy(entity);
	}

    @Override
    public void run() {
        logger.info("Execute rejected mission "+entity);
        try {
            call();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Execute rejected mission "+entity+" failed.");
        }
    }
}
