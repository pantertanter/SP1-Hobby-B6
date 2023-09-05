package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.model.Interest;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HobbyDAOTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static HobbyDAO hobbyDAO;

    @BeforeEach
    void setUp() {

        hobbyDAO = org.example.dao.HobbyDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());


        Interest interest1 = new Interest("News");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveHobby() {
    }

    @Test
    void readHobby() {
    }

    @Test
    void readAllHobbies() {
    }

    @Test
    void updateHobby() {
    }

    @Test
    void deleteHobby() {
    }
}