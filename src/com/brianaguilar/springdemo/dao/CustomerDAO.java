package com.brianaguilar.springdemo.dao;

import java.util.List;

import com.brianaguilar.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int customerId);
}
