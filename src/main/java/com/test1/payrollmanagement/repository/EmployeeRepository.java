package com.test1.payrollmanagement.repository;

import com.test1.payrollmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Tìm kiếm nhân viên theo tên
    List<Employee> findByNameContaining(String name);
}
