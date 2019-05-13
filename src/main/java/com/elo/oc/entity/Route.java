package com.elo.oc.entity;

import com.elo.oc.entity.Sector;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "route")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findRouteBySectorId",
                query = "from Route where sector_fk = :sectorId")
})
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "height")
    private Double height;


    @NotNull
    @Column(name = "bolts")
    private Integer bolts;

    @ManyToOne //plusieurs voies pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @ManyToOne //plusieurs voies pour un seul secteur
    @JoinColumn(name = "sector_fk")
    private Sector sector;

    @ManyToOne //plusieurs voies pour un niveau
    @JoinColumn(name = "grade_fk")
    private Grade grade;

    public Route() {
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

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
