package com.tunisiana.tutorials.bo;

import java.util.List;

import com.tunisiana.tutorials.model.Customer;

public interface CustomerBo {

	void addCustomer(Customer customer);

	List<Customer> findAllCustomer();

	Customer getCustomer(Integer customerId);
	
}