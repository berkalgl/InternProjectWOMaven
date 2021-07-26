/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.service;

import com.gobito.internproject.dao.GenericDAO;
import com.gobito.internproject.dao.UserDAO;
import com.gobito.internproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Integer>
        implements UserService {

    @Autowired
    private UserDAO userDAO;

    public UserServiceImpl() {

    }

    @Autowired
    public UserServiceImpl(GenericDAO<User, Integer> genericDao) {
        super(genericDao);
        this.userDAO = (UserDAO) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean verifyLogin(String username, String password) {
        User u = this.userDAO.findUserByUsername(username);
        return u != null && u.getPassword().equals(password);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
