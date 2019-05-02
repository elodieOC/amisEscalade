package com.elo.oc.entity;

import javax.persistence.*;

@Entity
@Table(name="user_asso_member")
public class UserAssoMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name="user_fk")
    private User user;

    public UserAssoMember() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
