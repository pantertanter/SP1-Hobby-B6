package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Person;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private static PersonDAO personDAO;

    @BeforeEach
    void setUp() {

        personDAO = PersonDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Person person1 = new Person("Alex");
        Person person2 = new Person("Bob");
        Person person3 = new Person("Carl");
        Person person4 = new Person("Dennis");
        Person person5 = new Person("Erik");

        personDAO.savePerson(person1);
        personDAO.savePerson(person2);
        personDAO.savePerson(person3);
        personDAO.savePerson(person4);
        personDAO.savePerson(person5);
    }

    @AfterEach
    void tearDown() {
        EntityManager em = HibernateConfigTest.getEntityManagerFactoryConfig().createEntityManager();

        try {
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM address").executeUpdate();
        em.createNativeQuery("DELETE FROM hobby").executeUpdate();
        em.createNativeQuery("DELETE FROM interest").executeUpdate();
        em.createNativeQuery("DELETE FROM person").executeUpdate();
        em.createNativeQuery("DELETE FROM person").executeUpdate();
        em.createNativeQuery("DELETE FROM personDetails").executeUpdate();
        em.createNativeQuery("DELETE FROM profession").executeUpdate();
        em.createNativeQuery("DELETE FROM zip").executeUpdate();
        em.createNativeQuery("DELETE FROM phone").executeUpdate();

        em.getTransaction().commit();
    }
        finally {
        em.close();
    }
}

    @Test
    void savePerson() {
        Person expected = new Person("Bente");
        personDAO.savePerson(expected);
        Person actual = personDAO.readPerson(expected.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void readPerson() {
    }

    @Test
    void readAllPersons() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void readAllPersonsByHobby() {
    }

    @Test
    void countOfPersonsWithHobby() {
    }

    @Test
    void readAllHobbiesAndCountOfInterested() {
    }

    @Test
    void readAllPersonsByCity() {
    }

    @Test
    void readAllPersonsByPhone() {
    }
}