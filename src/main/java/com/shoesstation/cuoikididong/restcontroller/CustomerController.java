package com.shoesstation.cuoikididong.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.entity.Customer;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.repository.CustomerRepository;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	@GetMapping
	public List<Customer> findAllCustomers(){
		return customerRepository.findAll();
	}
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable int id){
		Optional<Customer> customerOptional=customerRepository.findById(id);
		Customer customer=null;
		if (customerOptional.isPresent()) {
			customer=customerOptional.get();
		}else {
			throw new ResourceNotFoundException("Không tìm thấy khách hàng có mã id: " +id);
		}
		return customer;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customer saveCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		return customerRepository.save(customer);
	}
	
	
	@PutMapping("/{id}")
	public Customer updateCustomer (@PathVariable int id,@RequestBody Customer customer) {
		Optional<Customer> customerOptional=customerRepository.findById(id);
		Customer c=null;
		if (customerOptional.isPresent()) {
			c=customerOptional.get();
			customer.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Không tìm thấy khách hàng có id: " +id);
		}
		return customerRepository.save(customer);
	}
}
