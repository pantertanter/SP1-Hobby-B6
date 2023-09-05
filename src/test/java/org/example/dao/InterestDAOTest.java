package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.model.Interest;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterestDAOTest {
    private static InterestDAO interestDAO;

    @BeforeEach
    void setUp() {

        interestDAO = org.example.dao.InterestDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Interest interest1 = new Interest("Football");
        Interest interest2 = new Interest("Handball");
        Interest interest3 = new Interest("Tennis");
        Interest interest4 = new Interest("Golf");
        Interest interest5 = new Interest("Badminton");

        interestDAO.saveInterest(interest1);
        interestDAO.saveInterest(interest2);
        interestDAO.saveInterest(interest3);
        interestDAO.saveInterest(interest4);
        interestDAO.saveInterest(interest5);
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
    void saveInterest() {
        Interest expected = new Interest("Polo");
        interestDAO.saveInterest(expected);
        Interest actual = interestDAO.readInterest(expected.getName());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void readInterest() {
        Interest actual = interestDAO.readInterest("Football");
        assertEquals("Football", actual.getName());
    }

    @Test
    void readAllInterest() {
        List<Interest> interestList = interestDAO.readAllInterests();
        assertEquals(interestList.size(), 5);
    }

    @Test
    void updateInterest() {
        Interest interest = interestDAO.readInterest("Football");
        interest.setName("Soccer");
        interestDAO.updateInterest(interest);
        Interest actual = interestDAO.readInterest("Soccer");
        assertEquals("Soccer", actual.getName());
    }

    @Test
    void deleteInterest() {
        Interest interest = interestDAO.readInterest("Football");
        interestDAO.deleteInterest(interest);
        Interest actual = interestDAO.readInterest("Football");
        assertNull(actual);
    }
}