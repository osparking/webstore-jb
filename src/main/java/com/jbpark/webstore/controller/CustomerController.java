package com.jbpark.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	@RequestMapping("/customersDetail")
	public String listDetail(Model model) {
		model.addAttribute("customers", customerService.getAllCustomerDetail());
		return "customersDetail";
	}

	@RequestMapping(value = "/customers/add", method = RequestMethod.GET)
	public String getAddNewCustomerForm(@ModelAttribute("newCustomer") Customer newCustomer) {
		return "addCustomer";
	}

	@RequestMapping(value = "/customers/add", method = RequestMethod.POST)
	public String processAddNewCustomerForm(Model model, @ModelAttribute("newCustomer") Customer newCustomer) {
		try {
			customerService.addCustomer(newCustomer);
			return "redirect:/market/customers";
		} catch (DataAccessException e) {
			Long custId = newCustomer.getCustomerId();
			String errorMsg = "고객 ID '" + custId + "'는 사용 중입니다. 다른 ID를 선택하세요.";
			model.addAttribute("errorMsg", errorMsg);
			return "addCustomer";
		}
	}

	@RequestMapping("/customers")
	public String list(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}
}
