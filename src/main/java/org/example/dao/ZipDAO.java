package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Person;
import org.example.model.Zip;

import java.util.List;
import java.util.Set;

public class ZipDAO implements IZipDAO {

    private static EntityManagerFactory emf;

    private static ZipDAO instance;

    //singleton pattern
    public static ZipDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ZipDAO();
        }
        return instance;
    }

    @Override
    public Zip saveZip(Zip zip) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(zip);
            em.getTransaction().commit();
            return zip;
        }
    }

    @Override
    public Zip readZip(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Zip.class,id);
        }
    }

    @Override
    public List<Zip> readAllZips(){ // [US-7]
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Zip.findAll", Zip.class).getResultList();
        }
    }

    @Override
    public Zip updateZip(Zip zip) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(zip);
            em.getTransaction().commit();
            return zip;
        }
    }

    @Override
    public Zip deleteZip(Zip zip) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(zip);
            em.getTransaction().commit();
            return zip;
        }
    }

    @Override
    public void getPersonsInCity(int zipCode) { // [US-6]
       /* try (var em = emf.createEntityManager()) {
            Zip cityFound = em.find(Zip.class, zipCode);

            Set<Person> inHillerød = new HashSet<>();

            List<Address> addresses = hillerød.getAddresses();

            for (Address a : addresses) {
                inHillerød.add(a.getPersonDetails().getPerson());
            }

            for (Person p : inHillerød) {
                System.out.println("Lives In Hillerød: " + p);
            }
            *//*getAllHobbiesAndCountOfInterested();

            getAllZipsAndCities(); // [US-7] As a user I want to get a list of all postcodes and city names in Denmark
*//*
        }*/
    }

    @Override
    public List<Zip> getAllZipsAndCities() {
        return null;
    }
}
