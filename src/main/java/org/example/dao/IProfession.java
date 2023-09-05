package org.example.dao;

import org.example.model.Address;
import org.example.model.Hobby;
import org.example.model.Profession;

import java.util.List;

public interface IProfession {

    public Profession saveProfession(Profession profession);

    public Profession readProfession(int id);

    public List<Profession> readAllProfessions();

    public Profession updateProfession(Profession profession);

    public Profession deleteProfession(Profession profession);
}
