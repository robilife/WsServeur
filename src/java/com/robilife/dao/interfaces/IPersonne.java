/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robilife.dao.interfaces;

import com.robilife.entities.Personne;
import java.util.List;

/**
 *
 * @author khk
 */
public interface IPersonne {
    
    boolean save(Personne personne);
    boolean update(Long id, Personne personne);
    boolean delete(Long id);
    List<Personne> findAll();
    Personne getPersonneById(Long id);
    
}
