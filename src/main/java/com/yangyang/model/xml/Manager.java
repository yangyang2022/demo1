package com.yangyang.model.xml;

/**
 * Manager <--> Depart One2One
 */
public class Manager {
    private Integer managerId;
    private String managerName;

    private Depart depart;

    public Manager() {
    }

    public Manager(String managerName, Depart depart) {
        this.managerName = managerName;
        this.depart = depart;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }
}
