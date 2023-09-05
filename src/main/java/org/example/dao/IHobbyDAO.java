package org.example.dao;

import org.example.model.Hobby;

import java.util.List;

public interface IHobbyDAO {

    public Hobby saveHobby(Hobby hobby);

    public Hobby readHobby(String name);

    public List<Hobby> readAllHobbies();

    public Hobby updateHobby(Hobby hobby);

    public Hobby deleteHobby(Hobby hobby);
}
