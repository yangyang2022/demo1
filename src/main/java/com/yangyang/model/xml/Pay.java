package com.yangyang.model.xml;

public class Pay {
    private Integer monthPay;
    private Integer yearPay;
    private Integer vocationWithPay;

    public Pay() {
    }

    public Pay(Integer monthPay, Integer yearPay, Integer vocationWithPay) {
        this.monthPay = monthPay;
        this.yearPay = yearPay;
        this.vocationWithPay = vocationWithPay;
    }

    public Integer getMonthPay() {
        return monthPay;
    }

    public void setMonthPay(Integer monthPay) {
        this.monthPay = monthPay;
    }

    public Integer getYearPay() {
        return yearPay;
    }

    public void setYearPay(Integer yearPay) {
        this.yearPay = yearPay;
    }

    public Integer getVocationWithPay() {
        return vocationWithPay;
    }

    public void setVocationWithPay(Integer vocationWithPay) {
        this.vocationWithPay = vocationWithPay;
    }
}
