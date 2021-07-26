/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.service;

import com.gobito.internproject.dao.GenericDAO;
import com.gobito.internproject.dao.AddressDAO;
import com.gobito.internproject.model.Address;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EliteHunter
 */
@Service
@Component("addressService")
public class AddressServiceImpl extends GenericServiceImpl<Address, Integer>
        implements AddressService {

    @Autowired(required = true)
    private AddressDAO addressDAO;

    public AddressServiceImpl() {

    }

    @Autowired
    public AddressServiceImpl(GenericDAO<Address, Integer> genericDao) {
        super(genericDao);
        this.addressDAO = (AddressDAO) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Address> findAndSetID(List<Address> addressesToBeAdded) {
        for (Address retrievedAddress : this.getAll()) {
            for (Address addressToBeAdded : addressesToBeAdded) {
                if ((retrievedAddress.getCountry().equals(addressToBeAdded.getCountry()))
                        && (retrievedAddress.getPostCode().equals(addressToBeAdded.getPostCode()))
                        && (retrievedAddress.getState().equals(addressToBeAdded.getState()))
                        && (retrievedAddress.getStreet().equals(addressToBeAdded.getStreet()))) {
                    addressToBeAdded.setId(retrievedAddress.getId());
                }
            }
        }
        return addressesToBeAdded;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

}
