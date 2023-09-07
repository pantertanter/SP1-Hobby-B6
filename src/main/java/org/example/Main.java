package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.model.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static void main(String[] args) {

        persistNewPerson();

        findAllInfoAboutPerson();

        getAllPhonesForAPerson();

        getPersonsFromHobby();

        getPersonsInterestedFromHobby();

        getPersonsInCity();


    }

    private static void getPersonsInCity() {
        try(var em = emf.createEntityManager()){
            Zip hillerød = em.find(Zip.class,3400);

            Set<Person> inHillerød = new HashSet<>();

            List<Address> addresses = hillerød.getAddresses();

            for(Address a : addresses){
                inHillerød.add(a.getPersonDetails().getPerson());
            }

            for(Person p : inHillerød){
                System.out.println("Lives In Hillerød: " + p);
            }
        }
    }

    private static void getPersonsInterestedFromHobby() {
        try(var em = emf.createEntityManager()){
            Hobby modelTrains = em.find(Hobby.class,"Painting");

            System.out.println("Number of People Interested In Painting: " + modelTrains.getPersons().size());
        }
    }

    private static void getPersonsFromHobby() {
        try(var em = emf.createEntityManager()){
            Hobby modelTrains = em.find(Hobby.class,"Model-trains");

            for(Person p : modelTrains.getPersons()){
                System.out.println("PERSON INTERESTED IN MODEL TRAINS: " + p);
            }
        }
    }

    private static void getAllPhonesForAPerson() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, 1);
            person.getPersonDetails().getPhoneSet().forEach(System.out::println);
            em.getTransaction().commit();
        }
    }

    private static void findAllInfoAboutPerson() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, 1);
            System.out.println(person);
            em.getTransaction().commit();
        }
    }

    private static void persistNewPerson() {
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

        try (var em = emf.createEntityManager()) {
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

            System.out.println("Hello world!");
            }
    }
}