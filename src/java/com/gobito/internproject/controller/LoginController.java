/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gobito.internproject.controller;

import com.gobito.internproject.model.User;
import com.gobito.internproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @ModelAttribute("user")
    public User populateUser() {
        User user = new User();
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String init(@ModelAttribute("user") User u, @ModelAttribute("error") String message, @RequestParam(required = false) String authfailed, String logout,
            String denied, Model model) {
        model.addAttribute("user", u);
        if (logout != null) {
            message = "Logged Out successfully, login again to continue !";
        } else if (denied != null) {
            message = "Access denied for this user !";
        }
        model.addAttribute("error", message);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginFail(@ModelAttribute("user") User u, @RequestParam(required = false) String authfailed, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("user", u);
        redirectAttrs.addFlashAttribute("error", "Invalid username or password, try again !");
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String register(@ModelAttribute("user") User u, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("user", u);
        return "redirect:/register";
    }
    
}
