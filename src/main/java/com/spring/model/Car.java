package com.spring.model;

/**
 * Created by syy on 2017/1/25.
 */
public class Car {
    private String brand;
    private double price;
    private double typeCircle;

    public Car() {
        System.out.println("car constructor ...");
    }

    public void init(){
        System.out.println("car init ... ");
    }
    public void destrory(){
        System.out.println("car destory ... ");
    }

    public Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTypeCircle() {
        return typeCircle;
    }

    public void setTypeCircle(double typeCircle) {
        this.typeCircle = typeCircle;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", typeCircle=" + typeCircle +
                '}';
    }
}
