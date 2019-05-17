package com.elo.oc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>A topo is a physical book owned by a user.</h2>
 * <p>It can be available for lending to other users.</p>
 * <p>It contains the attributes:</p>
 * <ul>
 *     <li>id</li>
 *     <li>name</li>
 *     <li>description</li>
 *     <li>county</li>
 *     <li>city</li>
 *     <li>country</li>
 *     <li>dateRelease</li>
 *     <li>available</li>
 *     <li>user</li>
 * </ul>
 */
@Entity
@Table(name = "topo")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findTopoByUserId",
                query = "from Topo where climb_user_fk = :userId"),
        @org.hibernate.annotations.NamedQuery(name = "findTopoByCity",
                query = "from Topo where city = :city")
})
public class Topo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 255, message = "maximum 255 caractères")
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "county")
    private String county;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "country")
    private String country;

    @NotBlank
    @Column(name = "date_release")
    private String dateRelease;

    @NotNull
    @Column(name = "available")
    private Boolean available = false;

    @ManyToOne //plusieurs topos pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    public Topo() {
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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


    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
