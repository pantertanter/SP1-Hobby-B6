package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Interest;
import org.example.model.Profession;

import java.util.List;

public class ProfessionDAO implements IProfession{

    private static EntityManagerFactory emf;

    private static ProfessionDAO instance;

    public static ProfessionDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ProfessionDAO();
        }
        return instance;
    }

    @Override
    public Profession saveProfession(Profession profession) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(profession);
            em.getTransaction().commit();
            return profession;
        }
    }

    @Override
    public Profession readProfession(String name) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Profession.class,name);
        }
    }

    @Override
    public List<Profession> readAllProfessions() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Profession.findAll", Profession.class).getResultList();
        }
    }

    @Override
    public Profession updateProfession(Profession profession) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(profession);
            em.getTransaction().commit();
            return profession;
        }
    }

    @Override
    public Profession deleteProfession(Profession profession) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(profession);
            em.getTransaction().commit();
            return profession;
        }
    }
}