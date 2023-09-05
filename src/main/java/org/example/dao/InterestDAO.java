package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Interest;

import java.util.List;

public class InterestDAO implements IInterest {

    private static EntityManagerFactory emf;

    private static InterestDAO instance;

    //singleton pattern
    public static InterestDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new InterestDAO();
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
