/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robilife.dao.implementation;

import com.robilife.dao.interfaces.IPersonne;
import com.robilife.entities.Personne;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author khk
 */
public class PersonneImpl implements IPersonne{
    
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "TpWebServicePU" );
    
    @Override
    public boolean save(Personne personne) {
        
       EntityManager entitymanager = emfactory.createEntityManager( );
       entitymanager.getTransaction( ).begin( );
        try {
            entitymanager.persist(personne);
            entitymanager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entitymanager.getTransaction().rollback();
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean update(Long id, Personne personne) {
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        try {
            Personne p = getPersonneById(id);
            System.err.println(personne.getNom());
            System.err.println(personne.getPrenom());
            if (p != null) {
                p.setNom(personne.getNom());
                p.setPrenom(personne.getPrenom());
                entitymanager.getTransaction( ).commit( );
                return true;
            } else return false;
        } catch (Exception e) {
            entitymanager.getTransaction().rollback();
            System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }

    @Override
    public boolean delete(Long id) {
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        try {
            Personne p = getPersonneById(id);
            if (p != null) {
                
                if (!entitymanager.contains(p)) {
                    p = entitymanager.merge(p);
                }
                entitymanager.remove(p);
                entitymanager.getTransaction().commit();
            return true;
            } else return false;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public List<Personne> findAll() {
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        try {
            return entitymanager.createQuery("select p from Personne p", Personne.class).getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public Personne getPersonneById(Long id) {
        
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        try {
            Integer i = (int) (long) id;
            Personne p = entitymanager.find(Personne.class, i);
            return p;
            //return entitymanager.createQuery("select p from Personne p where p.id=:id", Personne.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}
