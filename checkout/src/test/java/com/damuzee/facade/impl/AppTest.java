package com.damuzee.facade.impl;

import java.sql.Timestamp;

import com.damuzee.common.Operation;

public class AppTest {

    public static void main(String[] args) {
       System.out.println(Operation.RETRY.equals(Operation.INCOME));
       Timestamp ts = new Timestamp(System.currentTimeMillis());
       System.out.println(ts);
    }

}
