package com.elo.oc.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RouteForm {
    @NotEmpty
    private String name;


    public RouteForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
