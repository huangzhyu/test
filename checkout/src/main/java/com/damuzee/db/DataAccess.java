package com.damuzee.db;

import java.util.List;

public interface DataAccess<T> {
    /**
     * 增加一条记录
     * @param t
     * @return 成功返回true
     */
    public boolean add(T t);
    public void delete(T t);
    public void update(T t);
    public T getFirst(T t);
    /**
     * 返回满足条件的所有记录
     * @param t
     * @return
     */
    public List<T> getALL(T t);
}
