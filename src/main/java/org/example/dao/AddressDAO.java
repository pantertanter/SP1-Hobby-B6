package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Address;

import java.util.List;

public class AddressDAO implements IAddressDAO{

    private static EntityManagerFactory emf;

    private static AddressDAO instance;

    //singleton pattern
    public static AddressDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new AddressDAO();
        }
        return instance;
    }


    @Override
    public Address saveAddress(Address address) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        }
    }

    @Override
    public Address readAddress(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Address.class,id);
        }
    }

    @Override
    public List<Address> readAllAddresses() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Address.findAll", Address.class).getResultList();
        }
    }

    @Override
    public Address updateAddress(Address address) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
            return address;
        }
    }

    @Override
    public Address deleteAddress(Address address) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return address;
        }
    }
}
