package com.jbpark.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping
	public String welcome(Model model) {
		model.addAttribute("greeting", "안녕!");
		model.addAttribute("tagline", "웹스토어");
		return "forward:/welcome/greeting";
//		return "redirect:/welcome/greeting";
	}

	@RequestMapping("/welcome/greeting")
	public String greeting(Model model) {
		model.addAttribute("tagline", "더조은학원");
		return "welcome";
	}
}
