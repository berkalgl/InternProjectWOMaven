/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.controller;

import com.gobito.internproject.model.Address;
import com.gobito.internproject.model.Person;
import com.gobito.internproject.service.AddressService;
import com.gobito.internproject.service.PersonService;
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
public class EmployeeController {

    private PersonService personService;
    private AddressService addressService;

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService ps) {
        this.personService = ps;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "addressService")
    public void setAddressService(AddressService as) {
        this.addressService = as;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listPersons(@ModelAttribute("person") Person p, Model model) {
        p.getAddresses().add(new Address());
        model.addAttribute("person", p);
        model.addAttribute("currAddingIndex", p.getAddresses().size() - 1);
        model.addAttribute("listPersons", this.personService.getAll());
        return "index";
    }

    @RequestMapping(value = "/person/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("person") Person p, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("person", p);
        return "redirect:/person";

    }

    //For add and update person both
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p) {
        p.getAddresses().remove(p.getAddresses().size() - 1);
        p.setAddresses(this.addressService.findAndSetID(p.getAddresses()));
        if (p.getId() == 0) {
            //new person, add it
            this.personService.add(p);
        } else {
            //existing person, call update
            this.personService.update(p);
        }

        return "redirect:/person";

    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        Person p = this.personService.get(id);
        this.personService.remove(p);
        return "redirect:/person";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Person p = this.personService.get(id);
        model.addAttribute("person", p);
        model.addAttribute("listPersons", this.personService.getAll());
        return "person";
    }
}
