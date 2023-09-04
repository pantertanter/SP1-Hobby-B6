package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.PersonDetails;

import java.util.List;

public class personDetailsDAO implements IPersonDetailsDAO{

    private static EntityManagerFactory emf;

    private static personDetailsDAO instance;

    //singleton pattern
    public static personDetailsDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new personDetailsDAO();
        }
        return instance;
    }


    @Override
    public PersonDetails savePersonDetails(PersonDetails personDetails) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(personDetails);
            em.getTransaction().commit();
            return personDetails;
        }
    }

    @Override
    public PersonDetails readPersonDetails(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(PersonDetails.class,id);
        }
    }

    @Override
    public List<PersonDetails> readAllPersonDetails() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("PersonDetails.findAll", PersonDetails.class).getResultList();
        }
    }

    @Override
    public PersonDetails updatePersonDetails(PersonDetails personDetails) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(personDetails);
            em.getTransaction().commit();
            return personDetails;
        }
    }

    @Override
    public PersonDetails deletePersonDetails(PersonDetails personDetails) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(personDetails);
            em.getTransaction().commit();
            return personDetails;
        }
    }
}
