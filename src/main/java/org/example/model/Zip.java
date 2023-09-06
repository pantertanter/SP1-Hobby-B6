package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "zip")
@NamedQueries({
        @NamedQuery(name = "Zip.findAll", query = "SELECT z FROM Zip z"),
        @NamedQuery(name = "Zip.DeleteAllRows", query = "DELETE FROM Zip"),
        //@NamedQuery(name = "Zip.findByCity", query = "SELECT z FROM Zip z WHERE z.city = :city")
})
public class Zip {

    @Id
    private int zipCode;

    @Column(name = "city", unique = true, nullable = false)
    private String city;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Address> addresses;

    public Zip(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
