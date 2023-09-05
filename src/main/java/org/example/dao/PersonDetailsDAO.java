package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.model.Person;
import org.example.model.PersonDetails;

import java.util.List;

public class PersonDetailsDAO implements IPersonDetailsDAO {

    private static EntityManagerFactory emf;

    private static PersonDetailsDAO instance;

    //singleton pattern
    public static PersonDetailsDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDetailsDAO();
        }
        return instance;
    }


    @Override
    public PersonDetails savePersonDetails(PersonDetails personDetails) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(personDetails);
            em.getTransaction().commit();
            return personDetails;
        }
    }

    @Override
    public PersonDetails readPersonDetails(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(PersonDetails.class, id);
        }
    }

    @Override
    public List<PersonDetails> readAllPersonDetails() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createNamedQuery("PersonDetails.findAll", PersonDetails.class).getResultList();
        }
    }

    @Override
    public PersonDetails updatePersonDetails(PersonDetails personDetails) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(personDetails);
            em.getTransaction().commit();
            return personDetails;
        }
    }

    @Override
    public PersonDetails deletePersonDetails(PersonDetails personDetails) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(personDetails);
            em.getTransaction().commit();
            return personDetails;
        }
    }

    @Override
    public List<PersonDetails> readAllPersonPhoneNumbers(Person person) {
        try (var em = emf.createEntityManager()) {
            TypedQuery<PersonDetails> query = em.createQuery(
                    "SELECT pd FROM PersonDetails pd WHERE pd.person = :person",
                    PersonDetails.class); // Change the entity name to match your entity class name

            query.setParameter("person", person); // Use the method parameter here

            List<PersonDetails> phoneNumbers = query.getResultList();
            phoneNumbers.forEach(System.out::println); // Print or process the phoneNumbers
            return phoneNumbers;
        }
    }
}
