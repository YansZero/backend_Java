package com.yanszero.pj01.entity;

import java.util.Date;

public class Oil {

    private Double oil_92;
    private Double oil_95;
    private Double oil_98;
    private Double diesel_fuel;
    private String company;
    private Date price_date;
    private Date createdDate;

    public Oil() {
    }

    public Oil(Double oil_92, Double oil_95, Double oil_98, Double diesel_fuel, String company, Date price_date, Date createdDate) {
        this.oil_92 = oil_92;
        this.oil_95 = oil_95;
        this.oil_98 = oil_98;
        this.diesel_fuel = diesel_fuel;
        this.company = company;
        this.price_date = price_date;
        this.createdDate = createdDate;
    }

    public Double getOil_92() {
        return oil_92;
    }

    public void setOil_92(Double oil_92) {
        this.oil_92 = oil_92;
    }

    public Double getOil_95() {
        return oil_95;
    }

    public void setOil_95(Double oil_95) {
        this.oil_95 = oil_95;
    }

    public Double getOil_98() {
        return oil_98;
    }

    public void setOil_98(Double oil_98) {
        this.oil_98 = oil_98;
    }

    public Double getDiesel_fuel() {
        return diesel_fuel;
    }

    public void setDiesel_fuel(Double diesel_fuel) {
        this.diesel_fuel = diesel_fuel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getPrice_date() {
        return price_date;
    }

    public void setPrice_date(Date price_date) {
        this.price_date = price_date;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
