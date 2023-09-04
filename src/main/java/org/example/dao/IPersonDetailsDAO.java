package org.example.dao;

import org.example.model.PersonDetails;

import java.util.List;

public interface IPersonDetailsDAO {

    public PersonDetails savePersonDetails(PersonDetails personDetails);

    public PersonDetails readPersonDetails(int id);

    public List<PersonDetails> readAllPersonDetails();

    public PersonDetails updatePersonDetails(PersonDetails personDetails);

    public PersonDetails deletePersonDetails(PersonDetails personDetails);

}
