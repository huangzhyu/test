package com.damuzee.strategy.impl;

import java.util.List;

import com.damuzee.model.Integral;
import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.Strategy;

public class CheckoutStrategy implements Strategy<ResultHolder> {
    @Override
    public ResultHolder doOperation(ResultHolder holder) {
        List<Integral> integrals = holder.getIntegrals();
        for(Integral i : integrals ){
            System.out.println("start to add:"+i);
        }
        
//      获取task的最新数据
//      将更新过程记录到日志，以便出现故障后恢复
//      执行更新操作
        
        return null;
    }


}
