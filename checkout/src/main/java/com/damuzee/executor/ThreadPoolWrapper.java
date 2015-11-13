package com.damuzee.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.damuzee.model.ResultHolder;

public class ThreadPoolWrapper {
    private RejectedHandler handler;
    
    public RejectedHandler getHandler() {
        return handler;
    }
    public void setHandler(RejectedHandler handler) {
        this.handler = handler;
    }

    public CompletionService<ResultHolder> getFixedThreadPool(int nThreads){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), handler);
        return new ExecutorCompletionService<ResultHolder>(executor); 
    }
}
