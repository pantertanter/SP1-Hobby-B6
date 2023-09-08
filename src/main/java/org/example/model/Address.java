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

    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Zip zip;

    //@MapsId
    @Getter
    @Setter
    @OneToOne
    private PersonDetails personDetails;

    public void setZip(Zip zip) { // Not bidirectional
        this.zip = zip;
    }

    public Address(String street, String houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
