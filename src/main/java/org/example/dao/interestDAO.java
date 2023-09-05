package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Address;
import org.example.model.Interest;
import org.example.model.PersonDetails;

import java.util.List;

public class interestDAO implements IInterest {

    private static EntityManagerFactory emf;

    private static interestDAO instance;

    //singleton pattern
    public static interestDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new interestDAO();
        }
        return instance;
    }



    @Override
    public Interest saveInterest(Interest interest) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(interest);
            em.getTransaction().commit();
            return interest;
        }
    }

    @Override
    public Interest readInterest(String name) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Interest.class,name);
        }
    }

    @Override
    public List<Interest> readAllInterests() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Interest.findAll", Interest.class).getResultList();
        }
    }

    @Override
    public Interest updateInterest(Interest interest) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(interest);
            em.getTransaction().commit();
            return interest;
        }
    }

    @Override
    public Interest deleteInterest(Interest interest) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(interest);
            em.getTransaction().commit();
            return interest;
        }
    }
}
