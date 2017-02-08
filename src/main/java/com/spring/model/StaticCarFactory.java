package com.spring.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syy on 2017/1/25.
 * static factory: invoke static method directly and return correspond object
 */
public class StaticCarFactory {

    private static Map<String,Car> cars = new HashMap<>();
    static {
        cars.put("Audi",new Car("Audi",30_0000));
        cars.put("Ford",new Car("Ford",40_0000));
    }

    public static Car getCar(String carName){
        return cars.get(carName);
    }
}
