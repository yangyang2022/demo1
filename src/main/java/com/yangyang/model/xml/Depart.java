package com.yangyang.model.xml;

public class Depart {
    private Integer depId;
    private String depName;

    private Manager manager;

    public Depart() {
    }

    public Depart(String depName, Manager manager) {
        this.depName = depName;
        this.manager = manager;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
