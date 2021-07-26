/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.dao;

import com.gobito.internproject.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("personDAO")
public class PersonDAOImpl extends GenericDAOImpl<Person, Integer>
        implements PersonDAO {
}
