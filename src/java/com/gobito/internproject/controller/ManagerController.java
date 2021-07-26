/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.controller;

import com.gobito.internproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerController {

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String listPersons(@ModelAttribute("user") User u, Model model) {
        if (u.getUsername() == null) {
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("user", u);
        }
        return "manager";
    }
}
