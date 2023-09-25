package com.smartContact.controller;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smartContact.dao.UserRepository;
import com.smartContact.entities.Contact;
import com.smartContact.entities.User;
import com.smartContact.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@Component
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@ModelAttribute
	public void CommonData(Model model, Principal principal) {
		String name=principal.getName();
		User user = repository.getUserByUserName(name);
		model.addAttribute(user);
	}
	
	@GetMapping("/user/dash")
	public String dashboard(Model model,Principal principal ) {
		model.addAttribute("title","User-Dashboard");
		
		return "userDashboard";

	}
	
	@GetMapping("/user/add-contact")
	public String AddContactForm(Model model) {
		model.addAttribute("title","Add Contact");
		model.addAttribute("contact",new Contact());
		
		
		
		return "normal/add_contact_form";
	}

	@PostMapping("/user/process-contact")
	public String processContact(@ModelAttribute Contact contact,@RequestParam("profileImages") MultipartFile image,@RequestParam("nick_name") String nickname,
			Principal principal,HttpSession httpSession) {
		
	try {
		
		
		String name = principal.getName();
		User user = repository.getUserByUserName(name);
		
		if (image.isEmpty()) {
			System.out.println("file is empty");
		}else {
			contact.setNickName(nickname);
			contact.setImage(image.getOriginalFilename());
			
			File file = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(file.getAbsolutePath()+File.separator+image.getOriginalFilename());
			
			Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
		}
		
		user.getContact().add(contact);
		contact.setUser(user);		
		repository.save(user);
		System.out.println("added to db"+" "+user.getContact());
		
		System.out.println(contact);
		
//		success message
		httpSession.setAttribute("message", new Message("Contact Added successfully. + Add More", "success"));
		
		;
	} catch (Exception e) {
	
//		error message
		httpSession.setAttribute("message", new Message("Please try again leter!!", "danger"));
	}
		return "normal/add_contact_form";
	}
	
}