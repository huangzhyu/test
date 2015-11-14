package com.damuzee.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.damuzee.model.FailedOrder;

public class FailedOrderAccessImpl extends DataAccessAdapter<FailedOrder> {
	private static final Logger logger = LoggerFactory.getLogger(FailedOrderAccessImpl.class);
	private static final String ADD_SQL="insert into unsuccess (orderId,type,time,status) values(?,?,?,?)";
	private static final String QUERY="select orderId,type,status from unsuccess where status=?";
	private static final String DELETE="delete from unsuccess where orderId=?";

	@Override
	public boolean add(FailedOrder order) {
		final FailedOrder tmpOrder=order;
		 return this.getTransactionTemplate().execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus paramTransactionStatus) {
				try {
					int flag = getJdbcTemplate().update(ADD_SQL, tmpOrder.getOrderId(),tmpOrder.getType(),tmpOrder.getTime(),tmpOrder.getStatus());
					return flag >0;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Failed to add "+tmpOrder+" in database.");
					logger.error(e.getLocalizedMessage());
					return false;
				}
			}
		});
	}
	
	@Override
	public List<FailedOrder> getALL(FailedOrder t) {
		List<FailedOrder> result = getJdbcTemplate().query(QUERY, new Object[]{t.getStatus()}, new RowMapper<FailedOrder>() {

			@Override
			public FailedOrder mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
				FailedOrder order = new FailedOrder();
				order.setOrderId(paramResultSet.getString(1));
				order.setType(paramResultSet.getByte(2));
				order.setStatus(paramResultSet.getByte(3));
				return order;
			}
		});
		return result;
		
	}

    @Override
    public void delete(FailedOrder t) {
        getJdbcTemplate().update(DELETE, t.getOrderId());
    }
	
	

}
