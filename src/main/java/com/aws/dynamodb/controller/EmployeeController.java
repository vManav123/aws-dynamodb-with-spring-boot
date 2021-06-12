package com.aws.dynamodb.controller;

import com.aws.dynamodb.model.Employee;
import com.aws.dynamodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(path = "/addEmployee")
    public String save(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    @GetMapping(path = "/getEmployee/{employeeId}")
    public Employee getEmploy(@PathVariable String employeeId)
    {
        return employeeRepository.getEmploy(employeeId);
    }

    @DeleteMapping(path = "/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId)
    {
        return employeeRepository.deleteEmployee(employeeId);
    }

    @PutMapping(path = "/updateEmployee}")
    public String updateEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.updateEmployee(employee);
    }
}
