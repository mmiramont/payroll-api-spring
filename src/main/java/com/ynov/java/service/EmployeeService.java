package com.ynov.java.service;

import com.ynov.java.controller.ExceptionController;
import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.Employee;

import java.util.Map;

/**
 * Created by Mathieu on 12/11/2015.
 * Classe gérant toute l'exécution des routes appelées pour la classe Employee
 */
public class EmployeeService extends ExceptionController{
    private static Map<Long, Employee> employeeMap = DatabaseClass.getMessages();

    /**
     * Route appelée en POST pour enregistrer un Employee
     * @param employee Employee - L'Employee à enregistrer
     * @return Employee - L'Employee crée avec le statut HTTP 201 CREATED
     * @throws SalaryBelowSmicException - L'Employee a un salaire inférieur au SMIC
     * @throws EmployeeNoNameException - L'Employee n'a pas de nom
     * @throws EmployeeNoSkillException - L'Employee n'a pas de compétences
     */
    public Employee addEmployee(Employee employee) throws SalaryBelowSmicException, EmployeeNoNameException, EmployeeNoSkillException {
        checkEmployee(employee);
        employee.setId(employeeMap.size()+1);
        employeeMap.put(Long.valueOf(employeeMap.size()+1), employee);
        return employee;
    }

    /**
     * Récupère l'Employee de la Database via son Id
     * @param id - L'id de l'Employee à récupérer
     * @return Employee - L'Employee récupéré
     * @throws EmployeeInvalidIdException - Aucun Employee n'a l'id passé en paramètre
     */
    public Employee getEmployee(int id) throws EmployeeInvalidIdException {
        if (employeeMap.get(Long.valueOf(id)) == null)
            throw new EmployeeInvalidIdException();
        return employeeMap.get(Long.valueOf(id));
    }

    /**
     * Modifie l'Employee de l'id passé en paramètre par l'Employee passé en paramètre
     * @param id - L'id de l'Employee à modifier
     * @param employee - Le nouvel Employee associé à cet id
     * @return Employee - La nouvelle valeur de l'Employee
     * @throws SalaryBelowSmicException - L'Employee a un salaire inférieur au SMIC
     * @throws EmployeeNoNameException - L'Employee n'a pas de nom
     * @throws EmployeeNoSkillException - L'Employee n'a pas de compétences
     */
    public Employee updateEmployee(int id, Employee employee) throws SalaryBelowSmicException, EmployeeNoNameException, EmployeeNoSkillException {
        checkEmployee(employee);
        employee.setId(id);
        employeeMap.replace(Long.valueOf(id), employee);
        return employee;
    }


    /**
     * Supprime l'Employee associé à l'id passé en paramètre
     * @param id - L'id de l'Employee à supprimer
     * @return Employee - L'Employee supprimé
     * @throws EmployeeInvalidIdBadReuestException - Aucun Employee n'a l'id passé en paramètre
     */
    public Employee deleteEmployee(int id) throws EmployeeInvalidIdBadReuestException {
        Employee employee = employeeMap.get(Long.valueOf(id));
        if (employee == null)
            throw new EmployeeInvalidIdBadReuestException();
        employeeMap.remove(Long.valueOf(id));
        return employee;
    }

    /**
     * Vérifie les valeurs de l'Employee
     * @param employee Employee - L'Employee à vérifier
     * @throws SalaryBelowSmicException - L'Employee a un salaire inférieur au SMIC
     * @throws EmployeeNoNameException - L'Employee n'a pas de nom
     * @throws EmployeeNoSkillException - L'Employee n'a pas de compétences
     */
    private void checkEmployee(Employee employee) throws SalaryBelowSmicException, EmployeeNoNameException, EmployeeNoSkillException {
        if(employee.getSalary() < Employee.SMIC)
            throw new SalaryBelowSmicException();
        if (employee.getName() == "" ||employee.getName() == null)
            throw new EmployeeNoNameException();
        if(employee.getSkills() == null)
            throw new EmployeeNoSkillException();
    }
}
