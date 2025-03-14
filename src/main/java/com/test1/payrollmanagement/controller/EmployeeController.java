package com.test1.payrollmanagement.controller;

import com.test1.payrollmanagement.model.Employee;
import com.test1.payrollmanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}
    @GetMapping
    public String listEmployees(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Employee> employees = (keyword != null && !keyword.isEmpty())
                ? employeeService.searchEmployees(keyword)
                : employeeService.getAllEmployees();

        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword);
        return "employee/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) return "redirect:/employees"; // Nếu không tìm thấy nhân viên, quay lại danh sách
        model.addAttribute("employee", employee);
        return "employee/edit"; // Trả về trang edit
    }



    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}