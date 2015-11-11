package com.damuzee.db;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class DataAccessAdapter<T> extends JdbcDaoSupport implements DataAccess<T>,TransactionCallback<T>{
    
    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }


    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<T> delete(T t, boolean isFetchDeletedRecords) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public T update(T t, boolean isFetchDeletedRecords) {
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


    @Override
    public abstract T doInTransaction(TransactionStatus paramTransactionStatus);

}
