package org.example.dao;

import org.example.model.Hobby;
import org.example.model.Person;

import java.util.List;

public interface IPersonDAO {

    public Person savePerson(Person person);

    public Person readPerson(int id);

    public List<Person> readAllPersons();

    public Person updatePerson(Person person);

    public Person deletePerson(Person person);

    public List<Person> readAllPersonsByHobby(Hobby hobby); // [US-3]

    public List<Person> countOfPersonsWithHobby(Hobby hobby); //[US-4]

    public List<Person> readAllHobbiesAndCountOfInterested(); //[US-5]

    public List<Person> readAllPersonsByCity(String city); //[US-6]

    public List<Person> readAllPersonsByPhone(String phoneNumber); //[US-8]






}
