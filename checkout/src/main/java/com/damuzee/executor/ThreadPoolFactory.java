package com.damuzee.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.damuzee.model.ResultHolder;

/**
 * 该类是由spring容器管理的单例类
 * @author huangzha
 *
 */
public class ThreadPoolFactory{
    private CompletionService<ResultHolder> checkoutThreadPool;
    private CompletionService<ResultHolder> divideTaskThreadPool;
    private final ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
    private ThreadPoolWrapper wrapper;
    

    public ThreadPoolFactory(int checkoutThreadPoolSize,int divideTaskThreadPoolSize,ThreadPoolWrapper wrapper) {
        checkoutThreadPool = wrapper.getFixedThreadPool(checkoutThreadPoolSize);
        divideTaskThreadPool = wrapper.getFixedThreadPool(divideTaskThreadPoolSize);
    }
    
    public ThreadPoolWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(ThreadPoolWrapper wrapper) {
        this.wrapper = wrapper;
    }



    public CompletionService<ResultHolder> getCheckoutThreadPool() {
        return checkoutThreadPool;
    }

    public CompletionService<ResultHolder> getDivideTaskThreadPool() {
        return divideTaskThreadPool;
    }

    public ScheduledExecutorService getScheduled() {
        return scheduled;
    }
}
