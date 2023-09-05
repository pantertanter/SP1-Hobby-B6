package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Address;
import org.example.model.Zip;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {

    private static AddressDAO addressDAO;

    @BeforeEach
    void setUp() {

        addressDAO = AddressDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

        Zip zip1 = new Zip(3400, "Hillerød");
        Zip zip2 = new Zip(2200, "København N");
        Zip zip3 = new Zip(2400, "København NV");
        Zip zip4 = new Zip(1453, "København K");
        Zip zip5 = new Zip(3460, "Birkerød");

        Address address1 = new Address("Hillerødvej","1",zip1);
        Address address2 = new Address("Nørregade","2",zip2);
        Address address3 = new Address("HC Andersens Boulevard","3",zip3);
        Address address4 = new Address("Sankt Peders Stræde","41a",zip4);
        Address address5 = new Address("Vestervang","6",zip5);


        addressDAO.saveAddress(address1);
        addressDAO.saveAddress(address2);
        addressDAO.saveAddress(address3);
        addressDAO.saveAddress(address4);
        addressDAO.saveAddress(address5);

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

            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @Test
    void saveAddress() {
        Address savedAddress = new Address("Hillerødvej","1",new Zip(123,"testcity"));
        addressDAO.saveAddress(savedAddress);
        Address actual = addressDAO.readAddress(1);

        assertEquals(savedAddress.getStreet(),actual.getStreet());
    }

    @Test
    void readAddress() {
        Address savedAddress = new Address("Hillerødvej","1",new Zip(123,"testcity"));
        addressDAO.saveAddress(savedAddress);
        Address actual = addressDAO.readAddress(1);

        assertEquals(savedAddress.getStreet(),actual.getStreet());
    }

    @Test
    void readAllAddresses() {
        List<Address> addressList = addressDAO.readAllAddresses();

        assertEquals("Hillerødvej",addressList.get(0).getStreet());
    }

    @Test
    void updateAddress() {
        Address address = addressDAO.readAddress(1);
        address.setStreet("hillerødvejen");

        addressDAO.updateAddress(address);

        assertEquals(addressDAO.readAddress(1).getStreet(),"hillerødvejen");
    }

    @Test
    void deleteAddress() {

        addressDAO.deleteAddress(addressDAO.readAddress(1));

        assertNull(addressDAO.readAddress(1));

    }
}