package com.jbpark.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping
	public String welcome(Model model) {
		model.addAttribute("greeting", "안녕");
		model.addAttribute("tagline", "웹스트어");
		return "welcome";
	}
}
