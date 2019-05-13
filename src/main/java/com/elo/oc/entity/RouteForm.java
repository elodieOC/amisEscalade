package com.elo.oc.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RouteForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String height;
    @NotEmpty
    private String bolts;
    @NotNull
    private Integer grade;

    public RouteForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBolts() {
        return bolts;
    }

    public void setBolts(String bolts) {
        this.bolts = bolts;
    }
}
