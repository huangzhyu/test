package com.damuzee.db;

import java.util.List;

import org.damuzee.mongo.MongoTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class DataAccessAdapter<T> extends JdbcDaoSupport implements DataAccess<T>{
    
    private TransactionTemplate transactionTemplate;
    private MongoTemplate mongoTemplate;

    public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}


	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }


    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not implemented yet.");        
    }


    @Override
    public void update(T t) {
        throw new UnsupportedOperationException("Not implemented yet.");        
    }


    @Override
    public T getFirst(T t) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    @Override
    public List<T> getALL(T t) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
