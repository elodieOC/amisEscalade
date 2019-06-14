package com.elo.oc.entity;

import org.apache.commons.collections.ArrayStack;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "climb_user")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findUserByUserName",
                query = "from User where username = :username"),
        @org.hibernate.annotations.NamedQuery(name = "findUserByEmail",
                query = "from User where email = :email"),
        @org.hibernate.annotations.NamedQuery(name = "findUserByRole",
                query = "from User where userRole = :userRole")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "password")
    @NotBlank
    private String password;


    @NotBlank
    @Column(name = "username", unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;
    
    @ManyToOne //plusieurs user pour un seul role
    @JoinColumn(name = "role_fk")
    private Role userRole;

    @Transient
    @NotBlank
    private String passwordConfirm;
    
    @Transient
    @NotNull
    private Integer memberOrNot;

    @OneToMany (mappedBy = "user")//attribut User user de Spot
    private List<Spot> spots = new ArrayList<>();

    @OneToMany (mappedBy = "user")//attribut User user de Spot
    private List<Sector> sectors = new ArrayList<>();

    @OneToMany (mappedBy = "user")//attribut User user de Spot
    private List<Route> routes = new ArrayList<>();

    @OneToMany (mappedBy = "user")//attribut User user de Spot
    private List<Length> lengths = new ArrayList<>();

    @OneToMany (mappedBy = "user")//attribut User user de Comment
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Topo> topos = new ArrayList<>();

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public User() {
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths(List<Length> lengths) {
        this.lengths = lengths;
    }

    public Integer getMemberOrNot() {
        return memberOrNot;
    }

    public void setMemberOrNot(Integer memberOrNot) {
        this.memberOrNot = memberOrNot;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
