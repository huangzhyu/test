package com.damuzee.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.TransactionStatus;

import com.damuzee.model.Integral;
import com.damuzee.model.ResultHolder;

public class IntegralAccessImpl extends DataAccessAdapter<ResultHolder> {
	private static  final Logger logger = LoggerFactory.getLogger(IntegralAccessImpl.class);
    private static final String ADD_SQL="insert into integral (userId,count,ratio,orderId,type,time,conversion) values(?,?,?,?,?,?,?)";
    private ThreadLocal<ResultHolder> local = new ThreadLocal<ResultHolder>();

    @Override
    public void add(ResultHolder holder) {
        local.set(holder);
        this.getTransactionTemplate().execute(this);
    }

    @Override
    public ResultHolder doInTransaction(TransactionStatus paramTransactionStatus) {
         final List<Integral> integrals = local.get().getIntegrals();
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
                    ps.setInt(7, integral.getConversion());
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
            e.printStackTrace();
        }finally{
            local.remove();
        }
        
        return null;
    }
    
	private void retry(List<Integral> integral) {
		
	}

}
