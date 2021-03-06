package com.elo.oc.entity;


import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grade")
@Immutable
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findGradeByName",
                query = "from Grade where name = :name"),
        @org.hibernate.annotations.NamedQuery(name = "findGradeByRatingId",
                query = "from Grade where rating_fk = :rating_fk")
})
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @ManyToOne //plusieurs cotation pour un seul niveau
    @JoinColumn(name = "rating_fk")
    private Rating rating;

    @OneToMany(mappedBy = "grade")
    private List<Length> lengths = new ArrayList<>();

      public Grade() {
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths(List<Length> lengths) {
        this.lengths = lengths;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
