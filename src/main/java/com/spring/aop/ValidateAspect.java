package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//Order 指定切面的优先级,值越小优先级越高
@Component//@Aspect@Order(1)
public class ValidateAspect {

    @Before("LoggingAspect.declareJoinPintExpression())")
    public void validateArgs(JoinPoint joinPoint){
        System.out.println("--> validate args begin: "+ Arrays.asList(joinPoint.getArgs()));
    }

    @After("execution(* com.spring.aop.*.*(..))")
    public void validateAfter(JoinPoint joinPoint){
        System.out.println("--> validate args end: "+ Arrays.asList(joinPoint.getArgs()));
    }

}
