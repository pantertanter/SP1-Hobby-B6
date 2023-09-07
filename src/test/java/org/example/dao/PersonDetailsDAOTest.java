package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonDetailsDAOTest {

    private static PersonDetailsDAO personDetailsDAO;

    @BeforeEach
    void setUp() {

        personDetailsDAO = PersonDetailsDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Person p1 = new Person("person1");
        Person p2 = new Person("person2");

        Zip zip1 = new Zip(3400, "Hillerød");
        Zip zip2 = new Zip(2200, "København N");

        Address address1 = new Address("Hillerødvej","1",zip1);
        Address address2 = new Address("Nørregade","2",zip2);

        Phone phone1 = new Phone("28367463");
        Phone phone2 = new Phone("73848493");

        Phone phone3 = new Phone("82763587");
        Phone phone4 = new Phone("87654567");

        Set<Phone> myPhoneSet = new HashSet<>();
        myPhoneSet.add(phone1);
        myPhoneSet.add(phone2);

        Set<Phone> myPhoneSet2 = new HashSet<>();
        myPhoneSet2.add(phone3);
        myPhoneSet2.add(phone4);

        PersonDetails pd1 = new PersonDetails("pd1@email.com", PersonDetails.Gender.MALE, 35, LocalDate.of(2023,9,6), myPhoneSet, address1);
        PersonDetails pd2 = new PersonDetails("pd2@email.com", PersonDetails.Gender.MALE, 40, LocalDate.of(2023,9,5), myPhoneSet2, address2);

        pd1.setPerson(p1);
        pd2.setPerson(p2);

        try(EntityManager em = HibernateConfigTest.getEntityManagerFactoryConfig().createEntityManager()){
            em.getTransaction().begin();

            em.persist(zip1);
            em.persist(zip2);
            em.persist(address1);
            em.persist(address2);

            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);
            em.persist(phone4);

            em.persist(pd1);
            em.persist(pd2);

            em.persist(p1);
            em.persist(p2);

        }


    }

    @AfterEach
    void tearDown() {

        EntityManager em = HibernateConfigTest.getEntityManagerFactoryConfig().createEntityManager();
        try {
            em.getTransaction().begin();

/*
            em.createNativeQuery("DELETE FROM person_hobby").executeUpdate();
            em.createNativeQuery("DELETE FROM person_interest").executeUpdate();
            em.createNativeQuery("DELETE FROM person").executeUpdate();
            em.createNativeQuery("DELETE FROM persondetails_phone").executeUpdate();
            em.createNativeQuery("DELETE FROM persondetails").executeUpdate();
            em.createNativeQuery("DELETE FROM address").executeUpdate();
            em.createNativeQuery("DELETE FROM zip_address").executeUpdate();
            em.createNativeQuery("DELETE FROM zip").executeUpdate();


            em.createNativeQuery("DELETE FROM hobby_person").executeUpdate();
            em.createNativeQuery("DELETE FROM hobby").executeUpdate();
            em.createNativeQuery("DELETE FROM interest_person").executeUpdate();
            em.createNativeQuery("DELETE FROM interest").executeUpdate();
            em.createNativeQuery("DELETE FROM phone").executeUpdate();
            em.createNativeQuery("DELETE FROM profession_person").executeUpdate();
            em.createNativeQuery("DELETE FROM profession").executeUpdate();
*/

            em.createNativeQuery("ALTER SEQUENCE address_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE phone_id_seq RESTART WITH 1").executeUpdate();

            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }

    @Test
    void savePersonDetails() {

        Zip zip3 = new Zip(1234, "testcity");
        Address address3 = new Address("teststreet","99",zip3);
        PersonDetails pd3 = new PersonDetails("smp@mail.com", PersonDetails.Gender.MALE, 26, LocalDate.now(), new HashSet<>(),address3);

        Person p3 = new Person("person3");
        p3.setPersonDetails(pd3);


        personDetailsDAO.savePersonDetails(pd3);

        try(EntityManager em = HibernateConfigTest.getEntityManagerFactoryConfig().createEntityManager()){
            em.getTransaction().begin();
            em.persist(p3);
            em.getTransaction().commit();
        }

        assertEquals(personDetailsDAO.readPersonDetails(3).getEmail(),"smp@mail.com");

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