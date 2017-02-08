package com.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {

    //object
    private ICalculator target;

    public CalculatorProxy(ICalculator target) {
        this.target = target;
    }

    public ICalculator getCalculator(){
        ICalculator proxy = null;

        //代理对象由哪一个代理对象加载
        ClassLoader loader = target.getClass().getClassLoader();

        //代理对象的哪些接口
        Class<?>[] interfaces = target.getClass().getInterfaces();

        //当调用代理对象其中的方法时要执行的代码
        InvocationHandler handler = new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before ... ");
                Object obj = method.invoke(target, args);
                System.out.println("after ... ");
                return obj;
            }
        };

        proxy = (ICalculator) Proxy.newProxyInstance(loader,interfaces,handler);

        return proxy;
    }
}
