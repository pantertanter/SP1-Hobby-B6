package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Zip;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZipDAOTest {

    private static ZipDAO zipDAO;

    @BeforeEach
    void setUp() {

        zipDAO = ZipDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Zip zip1 = new Zip(3400, "Hillerød");
        Zip zip2 = new Zip(2200, "København N");
        Zip zip3 = new Zip(2400, "København NV");
        Zip zip4 = new Zip(1453, "København K");
        Zip zip5 = new Zip(3460, "Birkerød");

        zipDAO.saveZip(zip1);
        zipDAO.saveZip(zip2);
        zipDAO.saveZip(zip3);
        zipDAO.saveZip(zip4);
        zipDAO.saveZip(zip5);
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
    void saveZip() {
        Zip expected = new Zip(2100, "København Ø");
        zipDAO.saveZip(expected);
        Zip actual = zipDAO.readZip(2100);
//        System.out.println(actual.getCity() + " " + expected.getCity());
        assertEquals(expected.getCity(), actual.getCity());

    }

    @Test
    void readZip() {
        Zip actual = zipDAO.readZip(3400);
        assertEquals("Hillerød", actual.getCity());
    }

    @Test
    void readAllZips() {
        List<Zip> zipList = zipDAO.readAllZips();
        assertEquals(zipList.size(), 5);
    }

    @Test
    void updateZip() {
        Zip zip = zipDAO.readZip(3460);
        zip.setCity("Smørum");
        zipDAO.updateZip(zip);
        Zip actual = zipDAO.readZip(3460);
        assertEquals("Smørum", actual.getCity());
    }

    @Test
    void deleteZip() {
        Zip zip = zipDAO.readZip(3460);
        zipDAO.deleteZip(zip);
        Zip actual = zipDAO.readZip(3460);
        assertNull(actual);
    }
}