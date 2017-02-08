package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component//@Aspect@Order(2)
public class LoggingAspect {

    //定义一个方法,用于声明切入表达式,一般不需要添加代码
    @Pointcut("execution( * com.spring.aop.*.*(..))")
    public void declareJoinPintExpression(){}

    @Before("declareJoinPintExpression()") //直接复制方法
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("before method:[　"+methodName+" ] begins! args: "+ Arrays.asList(args));
    }

    //在目标方法执行后执行,无论是否发生异常,在后置通知里面还不能访问方法返回结果
    //在返回通知里面可以访问方法执行结果
    @After("declareJoinPintExpression()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("method [ "+methodName+" ] is ending... ");
    }

    //在方法结束后的通知,所以可以访问方法的返回值
    @AfterReturning(value = "declareJoinPintExpression()",returning = "result")
    public void returningMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("method [ "+methodName+" ] is end,result is: "+result);
    }

    @AfterThrowing(value = "declareJoinPintExpression()",throwing = "ex")
    public void afterThorowing(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("method [ "+methodName+" ] is throwing exception: "+ex.getMessage());
    }


    /*
    //环绕通知需要携带这种类型的参数 ,类似动态代理的全过程,且环绕通知必须有返回值
    @Around("execution(* com.spring.aop.*.*(..))")
    public Object aroundMthod(ProceedingJoinPoint pjp ){

        Object res = null;
        String methodName = pjp.getSignature().getName();

        try {
            //前置通知
            System.out.println("--> begin: "+methodName+" args: "+Arrays.asList(pjp.getArgs()));
            //执行目标方法
            res = pjp.proceed();
            System.out.println("--> retuning: "+methodName+" args: "+Arrays.asList(pjp.getArgs())+" result: "+res);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("--> throwing: "+throwable.getMessage());
        }
        System.out.println("--> end: "+methodName+" args: "+Arrays.asList(pjp.getArgs()));
        return res;
    }

    */

}
