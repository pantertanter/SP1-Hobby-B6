package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Hobby;
import org.example.model.Person;
import org.example.model.Phone;

import java.util.List;
import java.util.Set;

public class PersonDAO implements IPersonDAO {

    private static EntityManagerFactory emf;

    private static PersonDAO instance;

    public static PersonDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDAO();
        }
        return instance;
    }

    @Override
    public Person savePerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public Person readPerson(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Person.class, id);
        }
    }

    @Override
    public List<Person> readAllPersons() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createNamedQuery("Person.findAll", Person.class).getResultList();
        }
    }

    @Override
    public Person updatePerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public Person deletePerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public Person findAllInfoAboutPerson(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            System.out.println(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public Set<Phone> getAllPhonesForAPerson(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            Set<Phone> phoneSet = person.getPersonDetails().getPhoneSet();
            em.getTransaction().commit();
            return phoneSet;
        }
    }

    @Override
    public Set<Person> getPersonsFromHobby(String hobbyName) {
        try (var em = emf.createEntityManager()) {
            Hobby hobby1 = em.find(Hobby.class, hobbyName);
                Set<Person> personSet = hobby1.getPersons();
                return personSet;
            }
        }

    @Override
    public List<Person> readAllPersonsByCity(String city) {

        return null;
    }

    @Override
    public List<Person> readAllPersonsByPhone(String phoneNumber) {
        return null;
    }


}
