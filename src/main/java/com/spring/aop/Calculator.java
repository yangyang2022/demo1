package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Calculator implements ICalculator {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }

    @Override
    public int multi(int a, int b) {
        return a*b;
    }

    @Override
    public int divide(int a, int b) {
        if(b != 0) return a/b;
        throw new RuntimeException("divide zero!");
    }
}
