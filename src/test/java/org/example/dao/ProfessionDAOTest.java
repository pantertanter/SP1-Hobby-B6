package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Profession;
import org.example.model.Zip;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessionDAOTest {

    private static ProfessionDAO professionDAO;

    @BeforeEach
    void setUp() {

        professionDAO = ProfessionDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Profession profession1 = new Profession("Software Engineer");
        Profession profession2 = new Profession("Software Architect");
        Profession profession3 = new Profession("Software Developer");
        Profession profession4 = new Profession("Software Tester");
        Profession profession5 = new Profession("Software Manager");

        professionDAO.saveProfession(profession1);
        professionDAO.saveProfession(profession2);
        professionDAO.saveProfession(profession3);
        professionDAO.saveProfession(profession4);
        professionDAO.saveProfession(profession5);
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
    void saveProfession() {
       Profession expected = new Profession("Painter");
        professionDAO.saveProfession(expected);
        Profession actual = professionDAO.readProfession("Painter");
//        System.out.println(actual.getCity() + " " + expected.getCity());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void readProfession() {
        Profession actual = professionDAO.readProfession("Software Engineer");
        assertEquals("Software Engineer", actual.getName());
    }

    @Test
    void readAllProfessions() {
        List<Profession> professionList = professionDAO.readAllProfessions();
        assertEquals(professionList.size(), 5);
    }

    @Test
    void updateProfession() {
        Profession profession = professionDAO.readProfession("Software Architect");
        profession.setDescription("A person who organizes and manages software projects.");
        professionDAO.updateProfession(profession);
        Profession actual = professionDAO.readProfession("Software Architect");
        assertEquals("A person who organizes and manages software projects.", actual.getDescription());
    }

    @Test
    void deleteProfession() {
        Profession profession = professionDAO.readProfession("Software Architect");
        professionDAO.deleteProfession(profession);
        Profession actual = professionDAO.readProfession("Software Architect");
        assertNull(actual);
    }
}