package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.model.Hobby;
import org.example.model.Interest;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {

    private static HobbyDAO hobbyDAO;

    @BeforeEach
    void setUp() {

        hobbyDAO = org.example.dao.HobbyDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());


        Hobby hobby1 = new Hobby("Model-trains");
        Hobby hobby2 = new Hobby("Painting");
        Hobby hobby3 = new Hobby("Computers");
        Hobby hobby4 = new Hobby("Skating");
        Hobby hobby5 = new Hobby("Pictures");

        hobbyDAO.saveHobby(hobby1);
        hobbyDAO.saveHobby(hobby2);
        hobbyDAO.saveHobby(hobby3);
        hobbyDAO.saveHobby(hobby4);
        hobbyDAO.saveHobby(hobby5);

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
    void saveHobby() {
        Hobby expected = new Hobby("Line-Dancing");
        hobbyDAO.saveHobby(expected);
        Hobby actual = hobbyDAO.readHobby(expected.getName());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void readHobby() {
        Hobby actual = hobbyDAO.readHobby("Painting");
        assertEquals("Painting", actual.getName());
    }

    @Test
    void readAllHobbies() {
        List<Hobby> hobbyList = hobbyDAO.readAllHobbies();
        assertEquals(hobbyList.size(), 5);
    }

    @Test
    void updateHobby() {
        Hobby hobby = hobbyDAO.readHobby("Painting");
        hobby.setName("pictures");
        hobbyDAO.updateHobby(hobby);
        Hobby actual = hobbyDAO.readHobby("pictures");
        assertEquals("pictures", actual.getName());
    }

    @Test
    void deleteHobby() {
        Hobby hobby = hobbyDAO.readHobby("Painting");
        hobbyDAO.deleteHobby(hobby);
        Hobby actual = hobbyDAO.readHobby("Painting");
        assertNull(actual);
    }
}