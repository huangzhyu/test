package com.damuzee.db;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.damuzee.model.Task;

public class TaskDAO extends JdbcDaoSupport {
	
	private TransactionTemplate transactionTemplate;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}


	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	public Task getSuperTask(Task subTask){
//	    dummy code
		if(subTask != null){
			if(subTask.get_id().equals("0") || subTask.get_id().equals("00")){
				Task t = new Task();
				t.set_id("0".concat(subTask.get_id()));
				return t;
			}
		}
		return null;
	}
	
	public void add(){
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				try {
					getJdbcTemplate().execute("insert into users (user_id,user_name) values (22,'hzy')");
					getJdbcTemplate().execute("insert into users (user_id,user_name) values (23,'hzy')");
				} catch (DataAccessException e) {
					e.printStackTrace();
					status.setRollbackOnly();
				}
			}
		});
	}
}
