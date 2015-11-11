package com.damuzee.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import com.damuzee.model.ResultHolder;

public enum ThreadPool {
	INSTANCE;
	
	private CompletionService<ResultHolder>  checkoutThreadPool= new ExecutorCompletionService<ResultHolder>(Executors.newFixedThreadPool(10)) ;
	private CompletionService<ResultHolder> divideTaskThreadool = new ExecutorCompletionService<ResultHolder>(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+2)) ;
	public CompletionService<ResultHolder> getUpdateTaskThreadPool() {
		return INSTANCE.checkoutThreadPool;
	}
	
	public CompletionService<ResultHolder> getDivideTaskThreadPool() {
		return INSTANCE.divideTaskThreadool;
	}
}
