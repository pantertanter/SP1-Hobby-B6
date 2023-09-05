package org.example.dao;

import org.example.model.PersonDetails;

import java.util.List;

public interface IInterest {

    public PersonDetails saveInterest(PersonDetails personDetails);

    public PersonDetails readInterest(int id);

    public List<PersonDetails> readAllInterest();

    public PersonDetails updateInterest(PersonDetails personDetails);

    public PersonDetails deleteInterest(PersonDetails personDetails);
}
