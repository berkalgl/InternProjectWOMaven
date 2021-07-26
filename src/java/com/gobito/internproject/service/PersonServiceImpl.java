/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.service;

import com.gobito.internproject.dao.GenericDAO;
import com.gobito.internproject.dao.PersonDAO;
import com.gobito.internproject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("personService")
public class PersonServiceImpl extends GenericServiceImpl<Person, Integer>
        implements PersonService {

    @Autowired(required = true)
    private PersonDAO personDAO;

    public PersonServiceImpl() {

    }

    @Autowired
    public PersonServiceImpl(GenericDAO<Person, Integer> genericDao) {
        super(genericDao);
        this.personDAO = (PersonDAO) genericDao;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

}
