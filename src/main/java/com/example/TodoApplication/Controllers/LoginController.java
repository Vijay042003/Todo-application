package com.example.TodoApplication.Controllers;

import com.example.TodoApplication.Entity.Users;
import com.example.TodoApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String login() {
        return "myLogin";
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String loginSubmit(@RequestParam String email,@RequestParam String password, ModelMap model) {
        List<Users> user = userRepository.findByEmail(email);
        if(!user.isEmpty()) {
            if(user.get(0).getPassword().equals(password)) {
                return "redirect:/todo-list?name="+user.get(0).getName();
            }
            else
            {
                model.put("msg", "Invalid Credentials Please enter correct login Credentials");
                return "myLogin";
            }

        }
        model.put("msg", "The provided email address is not registered. Please sign up to create a new account");
        return "myLogin";
    }
}
