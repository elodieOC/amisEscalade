package com.elo.oc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LengthForm {

    private String height;

    private String bolts;
    @NotNull
    private Integer grade;


    public LengthForm() {
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
