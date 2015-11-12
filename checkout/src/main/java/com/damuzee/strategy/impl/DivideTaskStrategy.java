package com.damuzee.strategy.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.common.Constant;
import com.damuzee.common.Utils;
import com.damuzee.db.AbstractMemberAccess;
import com.damuzee.model.Config;
import com.damuzee.model.Integral;
import com.damuzee.model.Member;
import com.damuzee.model.ResultHolder;
import com.damuzee.strategy.Strategy;

/**
 * 此类用来分解Task，获取当前Task的前两级Task（若存在），并计算出每级Task应得的返利
 * 
 * @author hadoop
 *
 */
public class DivideTaskStrategy implements Strategy<Member> {
    private static final Logger logger = LoggerFactory.getLogger(DivideTaskStrategy.class);
    private AbstractMemberAccess dataAccess;
    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public AbstractMemberAccess getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(AbstractMemberAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public ResultHolder doOperation(Member memberArg) {
        logger.info("Start to process order:"+memberArg.getOrderId());
        Member currentMember = dataAccess.getFirst(memberArg);
        if (currentMember == null) {
            logger.info("Cannot find the task creater then mission aborted.");
            return null;
        }
        logger.info("Task creater is:"+currentMember.getUserId());
        ArrayList<Member> integrals = new ArrayList<Member>();
        currentMember.setRatio(config.getSelfRatio());
        integrals.add(currentMember);
        if (!Utils.isNull(currentMember.getInvitedCode())) {
            Member superior = dataAccess.getSuperiorMember(currentMember);
            superior.setRatio(config.getSuperiorRatio());
            integrals.add(superior);
            logger.info("The superior of");
            if (!Utils.isNull(superior.getInvitedCode())) {
                Member finalSuperior = dataAccess.getSuperiorMember(superior);
                finalSuperior.setRatio(config.getFinalSuperiorRatio());
                integrals.add(finalSuperior);
            }
        }
        MathContext mc  = new MathContext(Constant.SCALE);        
        BigDecimal amount = currentMember.getAmount();
        BigDecimal bonus = new BigDecimal(config.getBonus());
        BigDecimal base = new BigDecimal(100);
        

        // integrals.add(integral);
        // integrals.add(integral2);
        // integrals.add(integral3);

        ResultHolder holder = new ResultHolder();
        // holder.setOrderId(orderId);
//        holder.setIntegrals(integrals);
        // logger.info("divide "+orderId+" completed");
        return holder;
    }

}
