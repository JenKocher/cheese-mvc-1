package org.launchcode.controllers;

import org.launchcode.models.CheeseData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Jen on 5/7/2017.
 */

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method=RequestMethod.GET)
    public String addUserForm(Model model) {
        model.addAttribute("title", "Add a User");
        //see if the following line will work in terms of
        //getting values to show up in form
        //How do you get values to show up in form if you don't send a user object in?
        User user = new User();
        model.addAttribute("user", user);
        return "user/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    /* Before model-binding
    public String processAddUserForm(Model model, @RequestParam String username,
                                                  @RequestParam String email,
                                                  @RequestParam String password,
                                                  @RequestParam String verify){
        User user = new User(username, email, password);
    */

    // After model-binding
    public String processAddUserForm(Model model, @ModelAttribute User user, @RequestParam String verify){
        model.addAttribute("user", user);
        if (verify.equals(user.getPassword())) {
            model.addAttribute("title", "User Successfully Added");
            return ("user/index");
        } else {
            model.addAttribute("title", "Make Corrections");
            return("user/add");
        }
    }
}