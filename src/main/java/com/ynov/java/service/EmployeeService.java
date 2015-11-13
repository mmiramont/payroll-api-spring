package com.ynov.java.service;

import com.ynov.java.controller.ExceptionController;
import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.Employee;

import java.util.Map;

/**
 * Created by Mathieu on 12/11/2015.
 */
public class EmployeeService extends ExceptionController{
    private static Map<Long, Employee> employeeMap = DatabaseClass.getMessages();

    public Employee addEmployee(Employee employee) throws BelowSmicException, EmployeeNoNameException, EmployeeNoSkillException {
        checkEmployee(employee);
        employee.setId(employeeMap.size()+1);
        employeeMap.put(Long.valueOf(employeeMap.size()+1), employee);
        return employee;
    }

    public Employee getEmployee(int id) throws EmployeeInvalidIdException {
        if (employeeMap.get(Long.valueOf(id)) == null)
            throw new EmployeeInvalidIdException();
        return employeeMap.get(Long.valueOf(id));
    }

//    public List<Employee> getEmployeeList() {
//        return new ArrayList<Employee>(employeeMap.values());
//    }

    public Employee updateEmployee(int id, Employee employee) throws BelowSmicException, EmployeeNoNameException, EmployeeNoSkillException {
        checkEmployee(employee);
        employee.setId(id);
        employeeMap.replace(Long.valueOf(id), employee);
        return employee;
    }

    private void checkEmployee(Employee employee) throws BelowSmicException, EmployeeNoNameException, EmployeeNoSkillException {
        if(employee.getSalary() < Employee.SMIC)
            throw new BelowSmicException();
        if (employee.getName() == "" ||employee.getName() == null)
            throw new EmployeeNoNameException();
        if(employee.getSkills() == null)
            throw new EmployeeNoSkillException();
    }

    public Employee deleteEmployee(int id) throws EmployeeInvalidIdBadReuestException {
        Employee employee = employeeMap.get(Long.valueOf(id));
        if (employee == null)
            throw new EmployeeInvalidIdBadReuestException();
        employeeMap.remove(Long.valueOf(id));
        return employee;
    }
}
