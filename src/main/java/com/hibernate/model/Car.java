package com.hibernate.model;

import com.hibernate.annotation.CaseModel;
import com.hibernate.annotation.CheckCase;
import com.yangyang.annotation.Past;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Car {

    @NotNull
    private String manufacture;

    @CheckCase(value = CaseModel.UPPER,message = "licensePlate must be {value}")
    @Size(min = 2,max = 10,message = " --> the size is '${validatedValue}' between {min} and {max} ")
    private String licensePlate;

    @Min(value = 2,message = "at least more than one!")
    private int seatCount;

    @Past
    private LocalDate localDate;

    //@Future
    //private LocalDate rentDate;

    @AssertTrue
    private boolean isRegister;

    @Valid
    private List<String> parts = new ArrayList<>();

    public void addPart(String part){
        parts.add(part);
    }

    @NotNull@Size(min = 3)
    public List<String> getParts(){return parts;}

    @ParameterScriptAssert(lang = "javascript",script = "arg1.size() <= arg0.size()*2")
    public void load(List<String> args0,List<String> args1){

    }
    public Car() {
    }

    public Car(String manufacture, String licensePlate, int seatCount) {
        this.manufacture = manufacture;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
    }

    public Car(String manufacture, String licensePlate, int seatCount, LocalDate localDate) {
        this.manufacture = manufacture;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.localDate = localDate;
    }

    public Car(String manufacture, String licensePlate, int seatCount, LocalDate localDate, boolean isRegister) {
        this.manufacture = manufacture;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.localDate = localDate;
        this.isRegister = isRegister;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
