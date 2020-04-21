package com.chumcraft.usefulwanderingtrader.utils;

public class test {
    private static test instance;
    
    private test(){

    }

    public static test getInstance(){
        if(test.instance == null){
            test.instance = new test();
        }
        return test.instance;
    }
}