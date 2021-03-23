package com.brianaguilar.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brianaguilar.springdemo.entity.Customer;

@Repository
class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// Get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Create a query
		Query<Customer> query = 
				currentSession.createQuery("from Customer",Customer.class);
		
		// Get the list of customers
		List<Customer> customers = query.getResultList();
		
		// Return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> query =
				currentSession.createQuery(
						"from Customer c where c.id="+id,
						Customer.class
				);
		Customer customer = query.getSingleResult();
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class, customerId);
		currentSession.delete(customer);
		
	}

}
