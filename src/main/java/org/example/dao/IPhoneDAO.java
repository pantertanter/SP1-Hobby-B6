package org.example.dao;

import org.example.model.Phone;

import java.util.List;

public interface IPhoneDAO {

    public Phone savePhone(Phone phone);

    public Phone readPhone(int id);

    public List<Phone> readAllPhones();

    public Phone updatePhone(Phone phone);

    public Phone deletePhone(Phone phone);
}