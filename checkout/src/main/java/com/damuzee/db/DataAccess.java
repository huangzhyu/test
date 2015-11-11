package com.damuzee.db;

import java.util.List;

public interface DataAccess<T> {
    /**
     * 增加一条记录
     * @param t
     */
    public void add(T t);
    /**
     * 删除一条或多条记录
     * @param t
     * @param isFetchDeletedRecords true:返回被删除的记录
     * @return 返回被删除的记录
     */
    public List<T> delete(T t,boolean isFetchDeletedRecords);
    /**
     * 更新一条或多条记录
     * @param t
     * @param isFetchDeletedRecords true:返回被更新的记录
     * @return
     */
    public T update(T t,boolean isFetchDeletedRecords);
    
    public T getFirst(T t);
    /**
     * 返回所有记录
     * @param t
     * @return
     */
    public List<T> getALL(T t);
}
