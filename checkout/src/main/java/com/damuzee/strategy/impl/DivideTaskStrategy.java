package com.damuzee.strategy.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.damuzee.common.Checkout;
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
        Member creater = dataAccess.getFirst(memberArg);
        if (creater == null) {
            logger.info("Cannot find the task creater then mission aborted.");
            return null;
        }
        logger.info("Task creater is:"+creater.getUserId());
        int platformPercent=100;
        ArrayList<Member> members = new ArrayList<Member>();
        creater.setRatio(config.getSelfRatio());
        platformPercent-=creater.getRatio();
        members.add(creater);
        if (!Utils.isNull(creater.getInvitedCode())) {
            Member superior = dataAccess.getSuperiorMember(creater);
            superior.setRatio(config.getSuperiorRatio());
            superior.setOrderId(creater.getOrderId());
            platformPercent-=superior.getRatio();
            members.add(superior);
            if (!Utils.isNull(superior.getInvitedCode())) {
                Member finalSuperior = dataAccess.getSuperiorMember(superior);
                finalSuperior.setRatio(config.getFinalSuperiorRatio());
                finalSuperior.setOrderId(creater.getOrderId());
                platformPercent-=finalSuperior.getRatio();
                members.add(finalSuperior);
            }
        }
        
        if(memberArg.getOperationType()==Checkout.INCOME.ordinal()){
            Member platform = new Member();
            platform.setUserId("0");
            platform.setRatio(platformPercent);
            platform.setOperationType(Checkout.INCOME);
            platform.setOrderId(memberArg.getOrderId());
            members.add(platform);
        }
        
        
        List<Integral> integrals = new ArrayList<Integral>(); 
        
        Integral integral = null;
        BigDecimal totalAmount = creater.getAmount();
        Timestamp currentTime=Utils.getCurrentTime();
        for (Member member : members) {
        	integral = new Integral(member);
        	integral.setCount(this.computeIntegral(totalAmount, member.getRatio()));
        	integral.setTime(currentTime);
        	integral.setConversion(config.getConversion());
        	integrals.add(integral);
		}

        ResultHolder holder = new ResultHolder();
        holder.setOrderId(memberArg.getOrderId());
        holder.setIntegrals(integrals);
        holder.setType(creater.getOperationType());
        holder.setTime(currentTime);
        return holder;
    }
    
	private int computeIntegral(BigDecimal totalAmount,int userRatio){
//		计算换算系数
		BigDecimal conversion = new BigDecimal(config.getConversion());
//		将百分比换算成小数,计算提成所占总金额的比例
		BigDecimal basePercent = Utils.ratioExchange(config.getBonus(), 100);
//		计算用户应得比例
		BigDecimal userPercent = Utils.ratioExchange(userRatio,100);
//		计算提成金额
		BigDecimal bonus = Utils.computeBonus(totalAmount, basePercent);
//		计算当前用户应得积分
		return  Utils.computeIntegral(bonus, userPercent, conversion);
	}

}
