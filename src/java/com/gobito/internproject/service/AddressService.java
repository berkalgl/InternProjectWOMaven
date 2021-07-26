/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.service;

import com.gobito.internproject.model.Address;
import java.util.List;

/**
 *
 * @author EliteHunter
 */
public interface AddressService extends GenericService<Address, Integer> {

    public List<Address> findAndSetID(List<Address> addressesToBeAdded);
}
