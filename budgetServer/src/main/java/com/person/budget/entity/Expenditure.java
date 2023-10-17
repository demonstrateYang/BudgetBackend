package com.person.budget.entity;

import java.io.Serializable;


public class Expenditure implements Serializable {
    private long  id;

    private double cost;

    private String name;

    private long categoryid;

    private String cost_time;

    private String imagename;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCost_time() {
        return cost_time;
    }

    public void setCost_time(String recordtime) {
        this.cost_time = recordtime;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "id=" + id +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                ", categoryid=" + categoryid +
                ", cost_time='" + cost_time + '\'' +
                ", imagename='" + imagename + '\'' +
                '}';
    }
}
