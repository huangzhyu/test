package com.damuzee.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import com.damuzee.model.ResultHolder;

/**
 * 该类是由spring容器管理的单例类
 * @author huangzha
 *
 */
public class ThreadPoolFactory {
    private static final int availableProcessors = Runtime.getRuntime().availableProcessors() + 1;
    private static CompletionService<ResultHolder> checkoutThreadPool;
    private static CompletionService<ResultHolder> divideTaskThreadPool;

    private static int checkoutThreadPoolSize = availableProcessors;
    private static int divideTaskThreadPoolSize = availableProcessors;

    static {
        checkoutThreadPool = new ExecutorCompletionService<ResultHolder>(Executors.newFixedThreadPool(checkoutThreadPoolSize));
        divideTaskThreadPool = new ExecutorCompletionService<ResultHolder>(Executors.newFixedThreadPool(divideTaskThreadPoolSize));
    }

    public void setCheckoutThreadPoolSize(int checkoutThreadPoolSize) {
        System.out.println("checkoutThreadPoolSize:"+checkoutThreadPoolSize);
        checkoutThreadPool = new ExecutorCompletionService<ResultHolder>(Executors.newFixedThreadPool(checkoutThreadPoolSize));
    }

    public void setDivideTaskThreadPoolSize(int divideTaskThreadPoolSize) {
        System.out.println("divideTaskThreadPoolSize:"+divideTaskThreadPoolSize);
        divideTaskThreadPool = new ExecutorCompletionService<ResultHolder>(Executors.newFixedThreadPool(divideTaskThreadPoolSize));
    }

    public CompletionService<ResultHolder> getCheckoutThreadPool() {
        return checkoutThreadPool;
    }

    public CompletionService<ResultHolder> getDivideTaskThreadPool() {
        return divideTaskThreadPool;
    }
}
