package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.model.Hobby;
import org.example.model.Person;

import java.util.List;
import java.util.Set;

public class HobbyDAO implements IHobbyDAO {

    private static EntityManagerFactory emf;

    private static HobbyDAO instance;

    public static HobbyDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
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
    public List<Hobby> readAllHobbies() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createNamedQuery("Hobby.findAll", Hobby.class).getResultList();
        }
    }

    @Override
    public Hobby updateHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
            return hobby;
        }
    }

    @Override
    public Hobby deleteHobby(Hobby hobby) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
            return hobby;
        }
    }

    @Override
    public int getPersonsInterestedFromHobby(String hobbyName) {
        try (var em = emf.createEntityManager()) {
            Hobby hobby1 = em.find(Hobby.class, hobbyName);
            return hobby1.getPersons().size();
        }
    }

    @Override
    public List<Hobby> getAllHobbiesAndCountOfInterested() {
        try (var em = emf.createEntityManager()) {
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
            List<Hobby> hobbies = query.getResultList();
            return hobbies;
        }
    }
}
