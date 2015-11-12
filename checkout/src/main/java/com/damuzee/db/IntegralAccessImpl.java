package com.damuzee.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.TransactionStatus;

import com.damuzee.model.Integral;

public class IntegralAccessImpl extends DataAccessAdapter<Integral> {
	private static  final Logger logger = LoggerFactory.getLogger(IntegralAccessImpl.class);
    private static final String ADD_SQL="insert into integral (userId,count,ratio,orderId,type,time) values(?,?,?,?,?,?)";
    ThreadLocal<List<Integral>> local = new ThreadLocal<List<Integral>>();

    @Override
    public void add(List<Integral> integral) {
        local.set(integral);
        this.getTransactionTemplate().execute(this);
    }

    @Override
    public Integral doInTransaction(TransactionStatus paramTransactionStatus) {
         final List<Integral> integrals = local.get();
        try{
//            getJdbcTemplate().update(ADD_SQL, integral.getUserId(), integral.getCount(), integral.getRatio(), integral.getOrderId(), integral.getType(),integral.getTime());
            getJdbcTemplate().batchUpdate(ADD_SQL, new BatchPreparedStatementSetter(){

                @Override
                public void setValues(PreparedStatement ps, int paramInt) throws SQLException {
                    Integral integral = integrals.get(paramInt);
                    ps.setString(1, integral.getUserId());
                    ps.setInt(2, integral.getCount());
                    ps.setInt(3, integral.getRatio());
                    ps.setString(4, integral.getOrderId());
                    ps.setByte(5, integral.getType());
                    ps.setTimestamp(6,integral.getTime());
                }

                @Override
                public int getBatchSize() {
                    return integrals.size();
                }});
        }catch(Exception e){
        	logger.info("==============start to log failed integral==============");
        	for(Integral integral : integrals){
        		logger.info(integral.toString());
        	}
        	logger.info("==============end to log failed integral==============");
            paramTransactionStatus.setRollbackOnly();
            retry(integrals);
        }finally{
            local.remove();
        }
        
        return null;
    }
    
	private void retry(List<Integral> integral) {
		
	}

}
