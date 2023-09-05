package org.example.dao;

import org.example.model.Person;
import org.example.model.Profession;

import java.util.List;

public interface IPerson {

    public Person savePerson(Person person);

    public Person readPerson(int id);

    public List<Person> readAllPersons();

    public Person updatePerson(Person person);

    public Person deletePerson(Person person);
}
