package com.elo.oc.entity;

import com.elo.oc.entity.Sector;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    //TODO enlever le length de route + annuler routeForm
    @NotNull
    @Column(name = "length")
    private Integer length;

    @ManyToOne //plusieurs voies pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @ManyToOne //plusieurs voies pour un seul secteur
    @JoinColumn(name = "sector_fk")
    private Sector sector;

    @OneToMany(mappedBy = "route")
    private List<Length> lengths = new ArrayList<>();

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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
