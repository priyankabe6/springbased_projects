package com.springboot.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.webapp.domain.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, String>{}
