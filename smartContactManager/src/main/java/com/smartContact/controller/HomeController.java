package com.smartContact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartContact.dao.UserRepository;
import com.smartContact.entities.User;
import com.smartContact.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@Component
public class HomeController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		User user = new User();
		user.setName("jaspal");
		user.setEmail("asd@");
		repository.save(user);
		return "working";
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "home - Smart Contact Manager");
		return "home";
	}

	@GetMapping("/about")
	public String About(Model model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "About";
	}

	@GetMapping("/signup")
	public String sign(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "sign";
	}
	
//	this handler for registring user
	@PostMapping("/do-register")
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {

			if (!agreement) {
				System.out.println("please check the checkox");
				
				throw new Exception("please check the checkox");
			}
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("user",user);
				System.out.println("validation errors are:- "+bindingResult.toString());
				return "sign";
				
			}

			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setImageUrl("default.png");
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setContact(null);
			repository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("successfully registered", "alert-success"));
			return "home";

		} catch (Exception e) {
			model.addAttribute("user", user);
			if(agreement) {
			session.setAttribute("message", new Message("something went wrong", "alert-danger"));
			}else
				session.setAttribute("message", new Message("Please Accept terms and conditions", "alert-danger"));
			
			return "sign";
		}
	}

	//	login controller
	@GetMapping("/login1")
	public String customLogin(Model model) {
		model.addAttribute("title","Login Page");
		return "login";
	}
}
