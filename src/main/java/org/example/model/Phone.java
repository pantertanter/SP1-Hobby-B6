package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "phone")
@NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private PersonDetails personDetails;

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
        if (personDetails != null) {
            personDetails.addPhone(this);
        }
    }

    public Phone(String phoneNumber, String description) {
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
