package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Hobby;

import java.util.List;

public class HobbyDAO implements IHobbyDAO {

    private static EntityManagerFactory emf;

    private static HobbyDAO instance;

    public static HobbyDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new HobbyDAO();
        }
        return instance;
    }

    @Override
    public Hobby saveHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        }
    }

    @Override
    public Hobby readHobby(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Hobby.class, name);
        }
    }

        @Override
        public List<Hobby> readAllHobbies () {
            try(EntityManager em = emf.createEntityManager()){
                return em.createNamedQuery("Hobby.findAll", Hobby.class).getResultList();
            }
        }

        @Override
        public Hobby updateHobby (Hobby hobby){
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                em.merge(hobby);
                em.getTransaction().commit();
                return hobby;
            }
        }

    @Override
    public Hobby deleteHobby(Hobby hobby) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
            return hobby;
        }
    }
}
