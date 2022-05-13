package com.shoesstation.cuoikididong.restcontroller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoesstation.cuoikididong.entity.Employee;
import com.shoesstation.cuoikididong.exception.ResourceNotFoundException;
import com.shoesstation.cuoikididong.exception.RestErrorResponse;
import com.shoesstation.cuoikididong.repository.EmployeeRepository;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@GetMapping
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable int id){
		Optional<Employee> employeeOptional=employeeRepository.findById(id);
		Employee employee=null;
		if (employeeOptional.isPresent()) {
			employee=employeeOptional.get();
		}else {
			throw new ResourceNotFoundException("Không tìm thấy khách hàng có mã id: " +id);
		}
		return employee;
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee saveCustomer(@RequestBody Employee employee) {
		employee.setId(0);
		return employeeRepository.save(employee);
	}
	
	
	@PutMapping("/{id}")
	public Employee updateCustomer (@PathVariable int id,@RequestBody Employee employee) {
		Optional<Employee> employeeOptional=employeeRepository.findById(id);
		Employee c=null;
		if (employeeOptional.isPresent()) {
			c=employeeOptional.get();
			employee.setId(c.getId());
		}else {
			throw new ResourceNotFoundException("Không tìm thấy khách hàng có id: " +id);
		}
		return employeeRepository.save(employee);
	}
	
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<RestErrorResponse> deleteEmployee(@PathVariable int id) {
		Optional<Employee> employeeOptional=employeeRepository.findById(id);
		if (!employeeOptional.isPresent()) {
			throw new ResourceNotFoundException("Không tìm thấy khách hàng có id: " +id);
		}
		employeeRepository.deleteById(id);
		return ResponseEntity.ok(new RestErrorResponse("Khách hàng có id: "+id+"đã được xóa!", HttpStatus.OK,LocalDateTime.now()));
		
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> authenticationEmployee(@RequestParam String phoneNumber,@RequestParam String password){
		Employee employee=new Employee();
		 employee=employeeRepository.findByPhoneNumber(phoneNumber);
		 if (employee!=null && employee.getPassword().equals(password)) {
			return ResponseEntity.ok(employee);
		}
		throw new ResourceNotFoundException("Số điện thoại hoặc mật khẩu không đúng");
		}
	
}
