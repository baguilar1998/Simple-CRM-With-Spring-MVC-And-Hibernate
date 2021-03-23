package com.brianaguilar.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brianaguilar.springdemo.dao.CustomerDAO;
import com.brianaguilar.springdemo.entity.Customer;
import com.brianaguilar.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		// Get Customers from the dao
		List<Customer> customers = customerService.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers",customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomerFromList(@RequestParam("customerId") int customerId) {
		System.out.println("Hit Delete");
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/list";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
}
