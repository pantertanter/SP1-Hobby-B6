package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.model.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
                EntityManager em = emf.createEntityManager();
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
                LocalDate.of(2023, 9, 7),
                myPhoneSet, address1);

        PersonDetails personDetails2 = new PersonDetails("Bob@Mail.com",
                PersonDetails.Gender.MALE, 40,
                LocalDate.of(2023, 9, 8),
                myPhoneSet2, address2);

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



        Person person1 = new Person("Alex", personDetails1,
                profession1, myHobbySet1,
                myInterestSet1);
        Person person2 = new Person("Bob", personDetails2,
                profession2, myHobbySet2,
                myInterestSet2);

        em.getTransaction().begin();
        em.persist(person1);
        em.getTransaction().commit();

        System.out.println("Hello world!");
    }
}