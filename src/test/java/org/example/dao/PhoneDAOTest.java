package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Phone;
import org.example.model.Zip;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneDAOTest {

    private static PhoneDAO phoneDAO;

    @BeforeEach
    void setUp() {

        phoneDAO = PhoneDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Phone p1 = new Phone("12345678", "Home");
        Phone p2 = new Phone("87654321", "Work");
        Phone p3 = new Phone("11223344", "Mobile");
        Phone p4 = new Phone("44332211", "Home");
        Phone p5 = new Phone("55555555", "Work");
        phoneDAO.savePhone(p1);
        phoneDAO.savePhone(p2);
        phoneDAO.savePhone(p3);
        phoneDAO.savePhone(p4);
        phoneDAO.savePhone(p5);
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

            em.createNativeQuery("ALTER SEQUENCE phone_id_seq RESTART WITH 1").executeUpdate();

            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @Test
    void savePhone() {
        Phone expected = new Phone("19395678", "Work");
        phoneDAO.savePhone(expected);
        Phone actual = phoneDAO.readPhone(expected.getId());
//        System.out.println(actual.getCity() + " " + expected.getCity());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }

    @Test
    void readPhone() {
        Phone actual = phoneDAO.readPhone(1);
        assertEquals("12345678", actual.getPhoneNumber());
    }

    @Test
    void readAllPhones() {
        List<Phone> phoneList = phoneDAO.readAllPhones();
        assertEquals(phoneList.size(), 5);
    }

    @Test
    void updatePhone() {
        Phone phone = phoneDAO.readPhone(4);
        phone.setPhoneNumber("44332211");
        phoneDAO.updatePhone(phone);
        Phone actual = phoneDAO.readPhone(4);
        assertEquals("44332211", actual.getPhoneNumber());
    }

    @Test
    void deletePhone() {
        Phone phone = phoneDAO.readPhone(5);
        phoneDAO.deletePhone(phone);
        Phone actual = phoneDAO.readPhone(5);
        assertNull(actual);
    }
}