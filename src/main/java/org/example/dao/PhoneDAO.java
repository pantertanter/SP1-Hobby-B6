package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Person;
import org.example.model.Phone;
import org.example.model.Zip;

import java.util.List;

public class PhoneDAO implements IPhoneDAO {

    private static EntityManagerFactory emf;

    private static PhoneDAO instance;

    //singleton pattern

    public static PhoneDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new PhoneDAO();
        }
        return instance;
    }

    @Override
    public Phone savePhone(Phone phone) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            return phone;
        }
    }

    @Override
    public Phone readPhone(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Phone.class,id);
        }
    }

    @Override
    public List<Phone> readAllPhones() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Phone.findAll", Phone.class).getResultList();
        }
    }

    @Override
    public Phone updatePhone(Phone phone) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
            return phone;
        }
    }

    @Override
    public Phone deletePhone(Phone phone) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
            return phone;
        }
    }

    @Override
    public Person getAllInfoFromPhoneNumber() {
        return null;
    }
}
