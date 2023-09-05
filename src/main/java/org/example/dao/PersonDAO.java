package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Hobby;
import org.example.model.Person;
import org.example.model.Zip;

import java.util.List;

public class PersonDAO implements IPersonDAO {

    private static EntityManagerFactory emf;

    private static PersonDAO instance;

    public static PersonDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new PersonDAO();
        }
        return instance;
    }

    @Override
    public Person savePerson(Person person) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public Person readPerson(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Person.class,id);
        }
    }

    @Override
    public List<Person> readAllPersons() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Person.findAll", Person.class).getResultList();
        }
    }

    @Override
    public Person updatePerson(Person person) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public Person deletePerson(Person person) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        }
    }

    @Override
    public List<Person> readAllPersonPhoneNumbers(Person person) { //[US-2]
        return null;
    }

    @Override
    public List<Person> readAllPersonsByHobby(Hobby hobby) { // [US-3]
        return null;
    }

    @Override
    public List<Person> countOfPersonsWithHobby(Hobby hobby) { //[US-4]
        return null;
    }

    @Override
    public List<Person> readAllHobbiesAndCountOfInterested() { //[US-5]
        return null;
    }

    @Override
    public List<Person> readAllPersonsByCity(String city) { //[US-6]
        return null;
    }

    @Override
    public List<Person> readAllPersonsByPhone(String phoneNumber) { //[US-8]
        return null;
    }
}
