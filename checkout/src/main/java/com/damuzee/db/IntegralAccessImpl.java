package com.damuzee.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.damuzee.model.Integral;
import com.damuzee.model.ResultHolder;

public class IntegralAccessImpl extends AbstractIntegralAccess {
	private static  final Logger logger = LoggerFactory.getLogger(IntegralAccessImpl.class);
    private static final String ADD_SQL="insert into integral (userId,count,ratio,orderId,type,time,conversion) values(?,?,?,?,?,?,?)";
    private static final String QUERY_AVAILABLE_INTEGRAL="select sum(count) from integral where userId=? and type=0";
    @Override
    public boolean add(ResultHolder holder) {
    	final ResultHolder tmpHolder =holder;
        return this.getTransactionTemplate().execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus paramTransactionStatus) {
		        final List<Integral> integrals = tmpHolder.getIntegrals();
		        int[] result=null;
		        try{
		        	result = getJdbcTemplate().batchUpdate(ADD_SQL, new BatchPreparedStatementSetter(){

		                @Override
		                public void setValues(PreparedStatement ps, int paramInt) throws SQLException {
		                    Integral integral = integrals.get(paramInt);
		                    ps.setString(1, integral.getUserId());
		                    ps.setLong(2, integral.getCount());
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
		        	logger.error(e.getLocalizedMessage());
		        	logger.info("==============end of log failed integral==============");
		            paramTransactionStatus.setRollbackOnly();
		            return false;
		        }
				return result.length>0;
			}
		});
    }
	@Override
	public long getSumIntegral(ResultHolder holder) {
		return getJdbcTemplate().query(QUERY_AVAILABLE_INTEGRAL, new Object[]{holder.getUserId()}, new ResultSetExtractor<Long>(){
			@Override
			public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				if(resultSet!=null && resultSet.next()){
					return resultSet.getLong(1);
				}
				return 0L;
			}});
	}

    
}
