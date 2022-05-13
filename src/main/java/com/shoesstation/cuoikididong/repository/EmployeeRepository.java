package com.shoesstation.cuoikididong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoesstation.cuoikididong.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public Employee findByPhoneNumber(String phoneNumber) ;

}
 