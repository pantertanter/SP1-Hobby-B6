package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.Zip;

import java.util.List;

public class ZipDAO implements IZipDao{

    private static EntityManagerFactory emf;

    private static ZipDAO instance;

    //singleton pattern
    public static ZipDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ZipDAO();
        }
        return instance;
    }

    @Override
    public Zip saveZip(Zip zip) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(zip);
            em.getTransaction().commit();
            return zip;
        }
    }

    @Override
    public Zip readZip(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Zip.class,id);
        }
    }

    @Override
    public List<Zip> readAllZips(){
        try(EntityManager em = emf.createEntityManager()){
            return em.createNamedQuery("Zip.findAll", Zip.class).getResultList();
        }
    }

    @Override
    public Zip updateZip(Zip zip) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(zip);
            em.getTransaction().commit();
            return zip;
        }
    }

    @Override
    public Zip deleteZip(Zip zip) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(zip);
            em.getTransaction().commit();
            return zip;
        }
    }
}
