package com.example.TodoApplication.Controllers;

import com.example.TodoApplication.Entity.Users;
import com.example.TodoApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RegisterController {

	private UserRepository userRepository;

	@Autowired
	public RegisterController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String myRegister() {
        return "myRegister";
    }

	@RequestMapping(value="/reg",method = RequestMethod.POST)
	public String submitRegister(@RequestParam String username, @RequestParam String email, @RequestParam String password,
								  Model model) {

		Users user = new Users(username, email, password);
		List<Users> userexist=userRepository.findByEmail(email);
		if(!userexist.isEmpty()) {
			model.addAttribute("msg",
					"An account with this email address already exists. Please log in instead.");
			return "myRegister";
		}
		userRepository.save(user);
		System.out.println(user);
		return "redirect:/log";
	}
}
