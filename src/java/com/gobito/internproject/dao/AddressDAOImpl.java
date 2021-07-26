/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.dao;

import com.gobito.internproject.model.Address;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("addressDAO")
public class AddressDAOImpl extends GenericDAOImpl<Address, Integer>
        implements AddressDAO {

}
