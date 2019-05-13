package com.elo.oc.entity;

import javax.validation.constraints.NotEmpty;

public class RouteForm {
    @NotEmpty
    private String height;
    @NotEmpty
    private String bolts;

    public RouteForm() {
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
