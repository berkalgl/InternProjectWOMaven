/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.dao;

import com.gobito.internproject.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User, Integer>
        implements UserDAO {

    @Override
    public User findUserByUsername(String username) {
        for (User u : this.getAll()) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
