package com.ynov.java.service;

import com.ynov.java.controller.ExceptionController;
import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mathieu on 12/11/2015.
 */
public class EmployeeService extends ExceptionController{
    private static Map<Long, Employee> employeeMap = DatabaseClass.getMessages();

    public Employee addEmployee(Employee employee) throws BelowSmicException, EmployeeNoNameException {
        if(employee.getSalary() < Employee.SMIC)
            throw new BelowSmicException();
        if (employee.getName() == "" ||employee.getName() == null)
            throw new EmployeeNoNameException();
        employee.setId(employeeMap.size()+1);
        employeeMap.put(Long.valueOf(employeeMap.size()+1), employee);
        return employee;
    }

    public Employee getEmployee(int id) throws EmployeeInvalidId{
        if (employeeMap.get(Long.valueOf(id)) == null)
            throw new EmployeeInvalidId();
        return employeeMap.get(Long.valueOf(id));
    }

//    public List<Employee> getEmployeeList() {
//        return new ArrayList<Employee>(employeeMap.values());
//    }

    public Employee updateEmployee(int id, Employee employee) {
        employee.setId(id);
        employeeMap.replace(Long.valueOf(id), employee);
        return employee;
    }

    public Employee deleteEmployee(int id) {
        Employee employee = employeeMap.get(Long.valueOf(id));
        employeeMap.remove(Long.valueOf(id));
        return employee;
    }
}
