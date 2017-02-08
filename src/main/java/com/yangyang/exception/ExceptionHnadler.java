package com.yangyang.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//这个注解专用于处理异常
@ControllerAdvice
public class ExceptionHnadler {

    /**
     * 加入Map时候不能用,需要用ModelAndView
     */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleException(Exception ex){

        System.out.println("--> occur exception: "+ex.getMessage());
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("ex",ex);

        System.out.println("ex:　"+ex.getMessage());
        mv.addObject("test","hello world");
        return mv;
    }

}
