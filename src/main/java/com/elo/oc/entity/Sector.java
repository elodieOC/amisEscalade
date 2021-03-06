package com.elo.oc.entity;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sector")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "findSectorByName",
                query = "from Sector where name = :name"),
        @org.hibernate.annotations.NamedQuery(name = "findSectorByUserId",
                query = "from Sector where climb_user_fk = :userId"),
        @org.hibernate.annotations.NamedQuery(name = "findSectorBySpotId",
                query = "from Sector where spot_fk = :spotId")
})
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 255, message = "maximum 255 caractères")
    @Column(name = "location")
    private String location;

    @NotBlank
    @Size(max = 500, message = "maximum 500 caractères")
    @Column(name = "access")
    private String access;

    @Lob
    @Nullable
    @Column(name = "image")
    private byte[] image;

    @Transient
    private MultipartFile imageFile;

    @Transient
    private String base64;

    @ManyToOne //plusieurs secteurs pour un seul user
    @JoinColumn(name = "climb_user_fk")
    private User user;

    @ManyToOne //plusieurs secteurs pour un seul spot
    @JoinColumn(name = "spot_fk")
    private Spot spot;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Route> routes = new ArrayList<>();

    public Sector() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public List<Route> getRoutes() {return routes; }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage( byte[] image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getBase64() {
        return this.base64 = Base64.encode(this.image);
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
