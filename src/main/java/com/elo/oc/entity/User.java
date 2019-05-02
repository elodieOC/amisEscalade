package com.elo.oc.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "climb_user")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findByUserName",
                query = "from User where username = :username"),
        @org.hibernate.annotations.NamedQuery(name = "findByEmail",
                query = "from User where email = :email")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Transient
    private String passwordConfirm;

    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Email
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany (mappedBy = "user") //attribut User user de Spot
    private List<Spot> spots = new ArrayList<>();

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", username=" + username + ", email=" + email +"]";
    }
}
