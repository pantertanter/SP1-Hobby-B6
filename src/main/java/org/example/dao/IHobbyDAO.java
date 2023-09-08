package org.example.dao;

import org.example.model.Hobby;
import org.example.model.Person;

import java.util.List;

public interface IHobbyDAO {

    public Hobby saveHobby(Hobby hobby);

    public Hobby readHobby(String name);

    public List<Hobby> readAllHobbies();

    public Hobby updateHobby(Hobby hobby);

    public Hobby deleteHobby(Hobby hobby);

    public List<Person> getPersonsFromHobby();                  // [US-3] As a user I want to get all persons with a given hobby

    public List<Person> getPersonsInterestedFromHobby();        // [US-4] As a user I want to get the number of people with a given hobby

    public List<Hobby> getAllHobbiesAndCountOfInterested();     // [US-5] As a user I want to get a list of all hobbies and a count of how many have that hobby

}
