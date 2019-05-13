package com.elo.oc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sector")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findSectorByName",
                query = "from Sector where name = :name"),
        @org.hibernate.annotations.NamedQuery(name = "findSectorByUserId",
                query = "from Sector where climb_user_fk = :userId"),
        @org.hibernate.annotations.NamedQuery(name = "findSectorBySpotId",
                query = "from Sector where spot_fk = :spotId")
})
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "location")
    private String location;

    @NotEmpty
    @Column(name = "access")
    private String access;

    @ManyToOne //plusieurs secteurs pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @ManyToOne //plusieurs secteurs pour un seul spot
    @JoinColumn(name = "spot_fk")
    private Spot spot;

    @OneToMany(mappedBy = "sector")
    private List<Route> routes = new ArrayList<>();

    public Sector() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public List<Route> getRoutes() {return routes; }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
