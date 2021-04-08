package com.jbpark.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.service.CustomerService;

@Controller
@RequestMapping("/market/*")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customers/add", method = RequestMethod.GET)
	public String getAddNewCustomerForm(@ModelAttribute("newCustomer") Customer newCustomer) {
		return "addCustomer";
	}

	@RequestMapping(value = "/customers/add", method = RequestMethod.POST)
	public String processAddNewCustomerForm(@ModelAttribute("newCustomer") Customer newCustomer) {
		customerService.addCustomer(newCustomer);
		return "redirect:/market/customers";
	}

	@RequestMapping("/customers")
	public String list(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}
}
