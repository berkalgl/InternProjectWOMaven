/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.service;

import com.gobito.internproject.model.User;

public interface UserService extends GenericService<User, Integer> {

    public boolean verifyLogin(String username, String password);
}
