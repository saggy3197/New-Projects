package com.isg.hr.newEmployee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isg.hr.newEmployee.entity.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,String>{

 public  Optional<Employee> findBypostinid(Integer postinid);


    
}
