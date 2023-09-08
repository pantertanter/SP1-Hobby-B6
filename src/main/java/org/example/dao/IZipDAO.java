package org.example.dao;

import org.example.model.Person;
import org.example.model.Zip;

import java.util.List;

public interface IZipDAO {
    public Zip saveZip(Zip zip);

    public Zip readZip(int id);

    public List<Zip> readAllZips();

    public Zip updateZip(Zip zip);

    public Zip deleteZip(Zip zip);

    public void getPersonsInCity(int zipCode);     // [US-6] As a user I want to get all persons living in a given city (i.e. 2800 Lyngby)

    public List<Zip> getAllZipsAndCities();                // [US-7] As a user I want to get a list of all postcodes and city names in Denmark

}
