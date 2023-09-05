package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "address")
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Setter
    @Column(name = "street", nullable = false)
    private String street;

    @Setter
    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Zip zip;

    //@MapsId
    @OneToOne
    private PersonDetails personDetails;

    public Address(String street, String houseNumber, Zip zip) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zip = zip;
    }
}
