package com.jbpark.webstore.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping
	public String welcome(Model model, RedirectAttributes redAttrs, Principal principal) {
		String greeting = "환영합니다!";
		String tagline = "세상에서 하나 뿐인 웹 가게";
		
		model.addAttribute("greeting", greeting);
		model.addAttribute("tagline", tagline);
		redAttrs.addFlashAttribute("greeting", greeting);
		redAttrs.addFlashAttribute("tagline", tagline);
	    if (principal != null) {
	    	model.addAttribute("username", principal.getName());
	    }
		
		return "welcome";
//		return "redirect:/welcome/greeting";
	}

	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "welcome";
	}
}
