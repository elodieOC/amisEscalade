package com.elo.oc.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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
                query = "from Spot where climb_user_fk = :userId")

})
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    @OneToMany (mappedBy = "spot")//attribut Spot spot de Comment
    private List<Comment> comments = new ArrayList<>();

    public Spot() {

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

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", user= " +
                "[id " + user.getId() +"], [name "+user.getUsername()+"], [email "+user.getEmail()+
                "], [role "+user.getUserRole().getRoleName()+"] "+
                '}';
    }
}
