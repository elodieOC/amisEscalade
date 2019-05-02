package com.elo.oc.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "spot")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findByCounty",
                query = "from Spot where county = :county"),
        @org.hibernate.annotations.NamedQuery(name = "findByCity",
                query = "from Spot where city = :city"),
        @org.hibernate.annotations.NamedQuery(name = "findByName",
                query = "from Spot where name = :name"),
        @org.hibernate.annotations.NamedQuery(name = "findByUserId",
                query = "from Spot where climb_user_fk = :userId")

})
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(name = "name", unique=true)
    private String name;

    @NotEmpty
    @Column(name = "city")
    private String city;

    @NotEmpty
    @Column(name = "county")
    private String county;

    @ManyToOne //plusieurs spot pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    public Spot() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
