/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.dao;

import com.gobito.internproject.model.User;

/**
 *
 * @author EliteHunter
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    public User findUserByUsername(String username);
}
