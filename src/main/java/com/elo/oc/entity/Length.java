package com.elo.oc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "length")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findLengthByRouteId",
                query = "from Length where route_fk = :routeId")
})
public class Length {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "height")
    private Double height;

    @NotNull
    @Column(name = "bolts")
    private Integer bolts;

    @ManyToOne //plusieurs longueurs pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @ManyToOne //plusieurs longueurs pour une seule voie
    @JoinColumn(name = "route_fk")
    private Route route;

    @ManyToOne //plusieurs longueurs pour un niveau
    @JoinColumn(name = "grade_fk")
    private Grade grade;

    public Length() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getBolts() {
        return bolts;
    }

    public void setBolts(Integer bolts) {
        this.bolts = bolts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
