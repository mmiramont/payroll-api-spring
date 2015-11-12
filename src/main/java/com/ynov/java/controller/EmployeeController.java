package com.ynov.java.controller;

import com.ynov.java.model.Employee;
import com.ynov.java.service.EmployeeService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mathieu on 12/11/2015.
 */
@RestController
@EnableAutoConfiguration
public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

//    @RequestMapping(value = "/employee", method = RequestMethod.GET)
//        List<Employee> getEmployees(){
//            return employeeService.getEmployeeList();
//        }


    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable("employeeId") int id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "employee/{employeeId}", method = RequestMethod.PUT)
    Employee updateEmployee(@PathVariable("employeeId") int id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @RequestMapping(value = "employee/{employeeId}", method = RequestMethod.DELETE)
    Employee deleteEmployee(@PathVariable("employeeId") int id){
        return employeeService.deleteEmployee(id);
    }

}
