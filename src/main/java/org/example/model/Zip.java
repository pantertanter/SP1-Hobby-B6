package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "zip")
@NamedQueries({
        @NamedQuery(name = "Zip.findAll", query = "SELECT z FROM Zip z"),
        @NamedQuery(name = "Zip.DeleteAllRows", query = "DELETE FROM Zip")})
public class Zip {

    @Id
    private int zipCode;

    @Setter
    @Column(name = "city", unique = true, nullable = false)
    private String city;

    @Setter
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Address> addresses;

    public Zip(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }
}
