package com.elo.oc.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {


   private String userName;
   private String name;
   private String county;
   private String city;
   private String nbrSector;
   private String nbrRoute;
   private String nbrLengths;

    public SearchForm() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
