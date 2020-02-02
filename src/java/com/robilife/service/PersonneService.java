/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robilife.service;

import com.robilife.dao.implementation.PersonneImpl;
import com.robilife.dao.interfaces.IPersonne;
import com.robilife.entities.Personne;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author khk
 */
@WebService(serviceName = "PersonneService")
public class PersonneService {
    
    private IPersonne iPersonne = new PersonneImpl();

    // web service method to add a person
    @WebMethod(operationName = "addPersonne")
    public boolean addPersonne(Personne personne) {
        return iPersonne.save(personne);
    }

    // web service method to update a person information
    @WebMethod(operationName = "updatePersonne")
    public boolean updatePersonne(Long id, Personne personne) {
        return iPersonne.update(id, personne);
    }

    // web service method to delete a person
    @WebMethod(operationName = "deletePersonne")
    public boolean deletePersonne(Long id) {
        return iPersonne.delete(id);
    }

    // web service method to get all person
    @WebMethod(operationName = "personneList")
    public List<Personne> personneList() {
        return iPersonne.findAll();
    }

    // web service method to get a person by his id
    @WebMethod(operationName = "getPersonneById")
    public Personne getPersonneById(Long id) {
        return iPersonne.getPersonneById(id);
    }
}
