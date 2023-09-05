package org.example.dao;

import org.example.model.PersonDetails;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDetailsDAOTest {

    private static PersonDetailsDAO personDetailsDAO;

    @BeforeEach
    void setUp() {

        personDetailsDAO = PersonDetailsDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        PersonDetails pd1 = new PersonDetails();
    }

    @AfterEach
    void tearDown() {
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