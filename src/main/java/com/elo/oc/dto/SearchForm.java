package com.elo.oc.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {

   private String name;
   private String county;
   private String city;
   private String nbrSector;
   private String gradeMin;
   private String gradeMax;
   private String nbrRoute;
   private String nbrLengths;
   private String username;

    public SearchForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGradeMin() {
        return gradeMin;
    }

    public void setGradeMin(String gradeMin) {
        this.gradeMin = gradeMin;
    }

    public String getGradeMax() {
        return gradeMax;
    }

    public void setGradeMax(String gradeMax) {
        this.gradeMax = gradeMax;
    }

    public String getNbrRoute() {
        return nbrRoute;
    }

    public void setNbrRoute(String nbrRoute) {
        this.nbrRoute = nbrRoute;
    }

    public String getNbrLengths() {
        return nbrLengths;
    }

    public void setNbrLengths(String nbrLengths) {
        this.nbrLengths = nbrLengths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNbrSector() {
        return nbrSector;
    }

    public void setNbrSector(String nbrSector) {
        this.nbrSector = nbrSector;
    }

}
