package org.example.dao;

import org.example.model.Profession;

import java.util.List;

public interface IProfessionDAO {

    public Profession saveProfession(Profession profession);

    public Profession readProfession(String name);

    public List<Profession> readAllProfessions();

    public Profession updateProfession(Profession profession);

    public Profession deleteProfession(Profession profession);
}
