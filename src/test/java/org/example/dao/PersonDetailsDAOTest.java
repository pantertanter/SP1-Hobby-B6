package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonDetailsDAOTest {

    private static PersonDetailsDAO personDetailsDAO;

    @BeforeEach
    void setUp() {

        personDetailsDAO = PersonDetailsDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Person p1 = new Person("person1","email1@mail.com", Person.Gender.MALE,20, LocalDate.now());
        Person p2 = new Person("person2","email2@mail.com", Person.Gender.FEMALE,30,LocalDate.now());


        Zip zip1 = new Zip(3400, "Hillerød");
        Zip zip2 = new Zip(2200, "København N");

        Address address1 = new Address("Hillerødvej","1",zip1);
        Address address2 = new Address("Nørregade","2",zip2);

        PersonDetails pd1 = new PersonDetails(address1);
        PersonDetails pd2 = new PersonDetails(address2);

        Phone phone1 = new Phone("12345678","work");
        Phone phone2 = new Phone("87654321","home");

        pd1.addPhone(phone1);
        pd2.addPhone(phone2);


        PersonDAO personDAO = PersonDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());
        personDAO.savePerson(p1);
        personDAO.savePerson(p2);


        p1.setPersonDetails(pd1);
        p2.setPersonDetails(pd2);

        personDAO.updatePerson(p1);
        personDAO.updatePerson(p2);
        /*
        personDetailsDAO.savePersonDetails(pd1);
        personDetailsDAO.savePersonDetails(pd2);*/


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

            em.createNativeQuery("ALTER SEQUENCE address_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1").executeUpdate();

            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }

    @Test
    void savePersonDetails() {

    }

    @Test
    void readPersonDetails() {
    }

    @Test
    void readAllPersonDetails() {
    }

    @Test
    void updatePersonDetails() {
    }

    @Test
    void deletePersonDetails() {
    }

    @Test
    void readAllPersonPhoneNumbers() {

    }
}