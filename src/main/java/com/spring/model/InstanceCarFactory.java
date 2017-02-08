package com.spring.model;

import java.util.HashMap;
import java.util.Map;

/**
 * instance factory method :instance factory before then invoke the method of factory
 */
public class InstanceCarFactory {

    private Map<String,Car> cars = null;

    public InstanceCarFactory(){
        cars = new HashMap<>();
        cars.put("Audi",new Car("Audi",30_0000));
        cars.put("Ford",new Car("Ford",40_0000));
    }

    public Car getCar(String name){
        return cars.get(name);
    }
}
