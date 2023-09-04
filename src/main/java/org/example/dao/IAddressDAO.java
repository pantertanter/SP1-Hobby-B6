package org.example.dao;

import org.example.model.Address;

import java.util.List;

public interface IAddressDAO {

    public Address saveAddress(Address address);

    public Address readAddress(int id);

    public List<Address> readAllAddresses();

    public Address updateAddress(Address address);

    public Address deleteAddress(Address address);

}
