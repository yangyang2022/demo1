package com.spring.model;

/**
 * Created by syy on 2017/1/25.
 */
public class City {
    private String brand;
    private double price;

    public City() {
    }

    public City(String brand, double price) {
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


    @Override
    public String toString() {
        return "City{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
