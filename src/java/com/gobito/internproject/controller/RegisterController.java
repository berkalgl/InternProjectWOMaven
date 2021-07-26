/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.controller;

import com.gobito.internproject.model.Address;
import com.gobito.internproject.model.Person;
import com.gobito.internproject.model.User;
import com.gobito.internproject.service.AddressService;
import com.gobito.internproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private UserService userService;
    private AddressService addressService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService us) {
        this.userService = us;
    }

    @Autowired(required = true)
    @Qualifier(value = "addressService")
    public void setAddressService(AddressService as) {
        this.addressService = as;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String init(@ModelAttribute("user") User u, Model model) {
        if (u.getUserInfo() == null) {
            u.setUserInfo(new Person());
        }
        u.getUserInfo().getAddresses().add(new Address());
        model.addAttribute("user", u);
        model.addAttribute("currAddingIndex", u.getUserInfo().getAddresses().size() - 1);
        return "register";
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("user") User u, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("user", u);
        return "redirect:/register";

    }

    //For add and update person both
    @RequestMapping(value = "/tryregister", method = RequestMethod.POST)
    public String tryRegister(@ModelAttribute("user") User u) {
        u.getUserInfo().getAddresses().remove(u.getUserInfo().getAddresses().size() - 1);
        u.getUserInfo().setAddresses(this.addressService.findAndSetID(u.getUserInfo().getAddresses()));

            this.userService.add(u);

            //existing person, call update
            this.userService.update(u);
 
        return "redirect:/login";

    }

    @RequestMapping("/editAddress/{id}")
    public String editAddress(@ModelAttribute("user") User u, @PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Address addressToEdit = u.getUserInfo().getAddresses().get(id);
        Address tempAddress = u.getUserInfo().getAddresses().get(u.getUserInfo().getAddresses().size() - 1);
        tempAddress.setCountry(addressToEdit.getCountry());
        tempAddress.setPostCode(addressToEdit.getPostCode());
        tempAddress.setState(addressToEdit.getState());
        tempAddress.setStreet(addressToEdit.getStreet());
        redirectAttrs.addFlashAttribute("user", u);
        redirectAttrs.addFlashAttribute("currEditingIndex", id);
        redirectAttrs.addFlashAttribute("isEdit", 1);
        return "redirect:/register";
    }

    @RequestMapping("/updateAddress/{id}")
    public String updateAddress(@ModelAttribute("user") User u, @PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        int size = u.getUserInfo().getAddresses().size();
        Address address = u.getUserInfo().getAddresses().get(size - 2);
        u.getUserInfo().getAddresses().remove(size - 1);
        u.getUserInfo().getAddresses().set(id, address);
        u.getUserInfo().getAddresses().remove(size - 2);
        redirectAttrs.addFlashAttribute("user", u);
        return "redirect:/register";
    }

    @RequestMapping("/cancelUpdate/{id}")
    public String cancelUpdate(@ModelAttribute("user") User u, @PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        Address addressToEdit = u.getUserInfo().getAddresses().get(id);
        Address tempAddress = u.getUserInfo().getAddresses().get(u.getUserInfo().getAddresses().size() - 1);
        tempAddress.setCountry(addressToEdit.getCountry());
        tempAddress.setPostCode(addressToEdit.getPostCode());
        tempAddress.setState(addressToEdit.getState());
        tempAddress.setStreet(addressToEdit.getStreet());
        redirectAttrs.addFlashAttribute("user", u);
        redirectAttrs.addFlashAttribute("currEditingIndex", id);
        redirectAttrs.addFlashAttribute("isEdit", 1);
        return "redirect:/register";
    }

    @RequestMapping("/removeAddress/{id}")
    public String removeAddress(@ModelAttribute("user") User u, @PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        u.getUserInfo().getAddresses().remove(u.getUserInfo().getAddresses().size() - 1);
        u.getUserInfo().getAddresses().remove(id);
        redirectAttrs.addFlashAttribute("user", u);
        return "redirect:/register";
    }
}
