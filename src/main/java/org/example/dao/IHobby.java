package org.example.dao;

import org.example.model.Address;
import org.example.model.Hobby;

import java.util.List;

public interface IHobby {

    public Address saveHobby(Hobby hobby);

    public Address readHobby(int id);

    public List<Hobby> readAllHobbies();

    public Hobby updateHobby(Hobby hobby);

    public Hobby deleteHobby(Hobby hobby);
}
