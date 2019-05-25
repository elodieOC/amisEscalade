package com.elo.oc.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "spot")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findSpotByCounty",
                query = "from Spot where county = :county"),
        @org.hibernate.annotations.NamedQuery(name = "findSpotByCity",
                query = "from Spot where city = :city"),
        @org.hibernate.annotations.NamedQuery(name = "findSpotByName",
                query = "from Spot where name = :name"),
        @org.hibernate.annotations.NamedQuery(name = "findSpotByUserId",
                query = "from Spot where climb_user_fk = :userId"),
        @org.hibernate.annotations.NamedQuery(name = "findSpotWithOfficialTag",
                query = "from Spot where tagged = :tag")

})
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name", unique=true)
    private String name;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "county")
    private String county;

    @NotNull
    @Column(name = "tagged")
    private Boolean tagged = false;

    @ManyToOne //plusieurs spot pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @OneToMany (mappedBy = "spot")//attribut Spot spot de Comment
    private List<Comment> comments = new ArrayList<>();

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany (mappedBy = "spot", fetch = FetchType.EAGER)//attribut Spot spot de Sector
    private Set<Sector> sectors = new HashSet<>();

    @Column(name = "nbrSecteurs")
    private int nbrSecteurs;

    public int getNbrSecteurs() {
        return nbrSecteurs;
    }

    public void setNbrSecteurs(int nbrSecteurs) {
        this.nbrSecteurs = nbrSecteurs;
    }

    /*@Fetch(FetchMode.SUBSELECT)
    @OneToMany (mappedBy = "spot")//attribut Spot spot de route
    private Set<Route> routes = new HashSet<>();*/

    public Spot() {

    }
/*
    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }*/

    public Boolean getTagged() {
        return tagged;
    }

    public void setTagged(Boolean tagged) {
        this.tagged = tagged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    @Override
    public String toString() {
        return "Spot{" +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
