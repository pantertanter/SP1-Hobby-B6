package org.example.dao;

import org.example.model.Hobby;
import org.example.model.Person;
import org.example.model.Phone;

import java.util.List;
import java.util.Set;

public interface IPersonDAO {

    public Person savePerson(Person person);

    public Person readPerson(int id);

    public List<Person> readAllPersons();

    public Person updatePerson(Person person);

    public Person deletePerson(Person person);

    public Person findAllInfoAboutPerson(int id);           // [US-1] As a user I want to get all the information about a person

    public Set<Phone> getAllPhonesForAPerson(int id);           // [US-2] As a user I want to get all phone numbers from a given person

    public Set<Person> getPersonsFromHobby(Hobby hobby); // [US-3]

    public List<Person> countOfPersonsWithHobby(Hobby hobby); //[US-4]

    public void readAllHobbiesAndCountOfInterested(); //[US-5]

    public List<Person> readAllPersonsByCity(String city); //[US-6]

    public List<Person> readAllPersonsByPhone(String phoneNumber); //[US-8]






}
