package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.*;
import org.example.testconfig.HibernateConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {

    private static PersonDAO personDAO;

    @BeforeEach
    void setUp() {

        personDAO = PersonDAO.getInstance(HibernateConfigTest.getEntityManagerFactoryConfig());

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

        Zip zip1 = new Zip(3400, "Hillerød");
        Zip zip2 = new Zip(2200, "København N");

        Address address1 = new Address("Hillerødvej","1",zip1);
        Address address2 = new Address("Nørregade","2",zip2);

        PersonDetails personDetails1 = new PersonDetails("Alex@Mail.com",
                PersonDetails.Gender.MALE, 35,
                LocalDate.of(2023, 9, 7),address1);
        personDetails1.setPhoneSet(myPhoneSet);

        PersonDetails personDetails2 = new PersonDetails("Bob@Mail.com",
                PersonDetails.Gender.MALE, 40,
                LocalDate.of(2023, 9, 8), address2);
        personDetails2.setPhoneSet(myPhoneSet2);


        Hobby hobby1 = new Hobby("Model-trains");
        Hobby hobby2 = new Hobby("Painting");
        Hobby hobby3 = new Hobby("Computers");
        Hobby hobby4 = new Hobby("Skating");

        Set<Hobby> myHobbySet1 = new HashSet<>();
        myHobbySet1.add(hobby1);
        myHobbySet1.add(hobby2);

        Set<Hobby> myHobbySet2 = new HashSet<>();
        myHobbySet2.add(hobby3);
        myHobbySet2.add(hobby4);

        Interest interest1 = new Interest("Sports");
        Interest interest2 = new Interest("Music");
        Interest interest3 = new Interest("Dancing");
        Interest interest4 = new Interest("Reading");

        Set<Interest> myInterestSet1 = new HashSet<>();
        myInterestSet1.add(interest1);
        myInterestSet1.add(interest2);

        Set<Interest> myInterestSet2 = new HashSet<>();
        myInterestSet2.add(interest3);
        myInterestSet2.add(interest4);

        Profession profession1 = new Profession("Teacher");
        Profession profession2 = new Profession("Engineer");

        Person person1 = new Person("Alex");
        Person person2 = new Person("Bob");

        person1.setPersonDetails(personDetails1);
        person1.setHobbies(myHobbySet1);
        person1.setInterests(myInterestSet1);
        person1.setProfession(profession1);

        person2.setPersonDetails(personDetails2);
        person2.setHobbies(myHobbySet2);
        person2.setInterests(myInterestSet2);
        person2.setProfession(profession2);

        personDetails1.setPerson(person1);
        personDetails2.setPerson(person2);

        EntityManagerFactory emf = HibernateConfigTest.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(phone1);
        em.persist(phone2);
        em.persist(phone3);
        em.persist(phone4);
        em.persist(zip1);
        em.persist(zip2);
        em.persist(hobby1);
        em.persist(hobby2);
        em.persist(hobby3);
        em.persist(hobby4);
        em.persist(interest1);
        em.persist(interest2);
        em.persist(interest3);
        em.persist(interest4);
        em.persist(profession1);
        em.persist(profession2);
        em.persist(address1);
        em.persist(address2);
        em.persist(personDetails1);
        em.persist(personDetails2);
        em.persist(person1);
        em.persist(person2);
        em.getTransaction().commit();

    }

    @AfterEach
    void tearDown() {
       /* EntityManager em = HibernateConfigTest.getEntityManagerFactoryConfig().createEntityManager();

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

            em.createNativeQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1").executeUpdate();


            em.getTransaction().commit();
    }
        finally {
        em.close();
    }*/
}

    @Test
    void savePerson() {
        Person expected = new Person("Bente");
        personDAO.savePerson(expected);
        Person actual = personDAO.readPerson(expected.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void readPerson() {
        Person actual = personDAO.readPerson(1); //[US-1]
        assertEquals("Teacher", actual.getProfession().getName());
        System.out.println(actual);
    }

    @Test
    void readAllPersons() {
        List<Person> personList = personDAO.readAllPersons();
        assertEquals(personList.size(), 2);
    }

    @Test
    void updatePerson() {
        Person person = personDAO.readPerson(1);
        person.setName("Åge");
        personDAO.updatePerson(person);
        Person actual = personDAO.readPerson(1);
        assertEquals("Åge", actual.getName());
    }

    @Test
    void deletePerson() {
        Person person = personDAO.readPerson(1);
        personDAO.deletePerson(person);
        Person actual = personDAO.readPerson(1);
        assertNull(actual);
    }

    @Test
    void readAllPersonsByHobby() {

    }

    @Test
    void countOfPersonsWithHobby() {
    }

    @Test
    void readAllHobbiesAndCountOfInterested() {
    }

    @Test
    void readAllPersonsByCity() {
    }

    @Test
    void readAllPersonsByPhone() {
    }
}