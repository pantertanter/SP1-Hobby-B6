package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "zip")
@NamedQueries({
        @NamedQuery(name = "Zip.findAll", query = "SELECT z FROM Zip z"),
        @NamedQuery(name = "Zip.DeleteAllRows", query = "DELETE FROM Zip"),
        //@NamedQuery(name = "Zip.findByCity", query = "SELECT z FROM Zip z WHERE z.city = :city")
})
public class Zip {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     */

    //måske er det lidt meningsløst med en auto gen primary key når vi har unique zip også
    @Id
    @Column(name = "zipCode", unique = true, nullable = false)
    private int zipCode;

    @Column(name = "city", unique = true, nullable = false)
    private String city;

    @OneToMany
    private List<Address> addresses;

    public Zip(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String smørum) {
        this.city = smørum;
    }
}
