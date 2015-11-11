package com.damuzee.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.TransactionStatus;

import com.damuzee.model.Integral;

public class IntegralDataAccessImpl extends DataAccessAdapter<Integral> {
    
    private static final String ADD_SQL="insert into integral (userId,count,ratio,orderId,type,time) values(?,?,?,?,?,?)";
    ThreadLocal<Integral> local = new ThreadLocal<Integral>();

    @Override
    public void add(Integral integral) {
        local.set(integral);
        this.getTransactionTemplate().execute(this);
    }

    @Override
    public Integral doInTransaction(TransactionStatus paramTransactionStatus) {
        final Integral integral = local.get();
        final List<Integral> parents = integral.getParents();
        //添加到parents集合是为了方便统一处理
        parents.add(integral);
        try{
//            getJdbcTemplate().update(ADD_SQL, integral.getUserId(), integral.getCount(), integral.getRatio(), integral.getOrderId(), integral.getType(),integral.getTime());
            getJdbcTemplate().batchUpdate(ADD_SQL, new BatchPreparedStatementSetter(){

                @Override
                public void setValues(PreparedStatement ps, int paramInt) throws SQLException {
                    Integral integral = parents.get(paramInt);
                    ps.setString(1, integral.getUserId());
                    ps.setInt(2, integral.getCount());
                    ps.setInt(3, integral.getRatio());
                    ps.setString(4, integral.getOrderId());
                    ps.setByte(5, integral.getType());
                    ps.setTimestamp(6,integral.getTime());
                }

                @Override
                public int getBatchSize() {
                    return parents.size();
                }});
        }catch(Exception e){
            e.printStackTrace();
            paramTransactionStatus.setRollbackOnly();
        }finally{
            local.remove();
        }
        
        return null;
    }

}
