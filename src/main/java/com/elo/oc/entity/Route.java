package com.elo.oc.entity;

import com.elo.oc.entity.Sector;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findRouteBySectorId",
                query = "from Route where sector_fk = :sectorId"),
        @org.hibernate.annotations.NamedQuery(name = "findRouteBySpotId",
                query = "from Route where spot_fk = :spotId")
})
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @ManyToOne //plusieurs voies pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @ManyToOne //plusieurs voies pour un seul secteur
    @JoinColumn(name = "sector_fk")
    private Sector sector;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Length> lengths = new ArrayList<>();

    @Transient
    private String gradeMin;
    @Transient
    private  String gradeMax;

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

    public Route() {
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths(List<Length> lengths) {
        this.lengths = lengths;
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

}
