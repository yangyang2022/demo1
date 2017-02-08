package com.spring.gnericExtend;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by syy on 2017/1/25.
 */
public class BaseService<T> {

    @Autowired
    private BaseRepository<T> repository;

    public void add(){
        System.out.println("add ... ");
        System.out.println(repository);
    }
}
