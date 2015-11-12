package com.damuzee.model;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class MyObject {
    private volatile Book book;

    private static final AtomicReferenceFieldUpdater<MyObject,Book> updater =
            AtomicReferenceFieldUpdater.newUpdater( 
                       MyObject.class, Book.class, "book" );
    
    
    public MyObject(Book book){
        this.book=book;
    }

    public Book getWhatImReading()
    {
        return book;
    }

    public void setWhatImReading( Book oldValue,Book whatImReading )
    {
        if(updater.compareAndSet( this, oldValue, whatImReading )){
            System.out.println(whatImReading.getName().concat("===============修改成功！"));
        }else{
            System.out.println(whatImReading.getName().concat("=======修改失败！"));
        }
    }
}
