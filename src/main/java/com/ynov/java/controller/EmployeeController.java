package com.ynov.java.controller;

import com.ynov.java.model.Employee;
import com.ynov.java.service.EmployeeService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mathieu on 12/11/2015.
 * Controlleur de la classe Employee.
 * Contient toutes les routes de la classe.
 * Chaque route passe par une méthode du service associé
 */
@RestController
@EnableAutoConfiguration
public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();

    /**
     * Route appelée en POST pour enregistrer un Employee
     * @param employee Employee - L'Employee à enregistrer
     * @return Employee - L'Employee crée avec le statut HTTP 201 CREATED
     * @throws ExceptionController.SalaryBelowSmicException - L'Employee a un salaire inférieur au SMIC
     * @throws ExceptionController.EmployeeNoNameException - L'Employee n'a pas de nom
     * @throws ExceptionController.EmployeeNoSkillException - L'Employee n'a pas de compétences
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Employee addEmployee(@RequestBody Employee employee) throws ExceptionController.SalaryBelowSmicException, ExceptionController.EmployeeNoNameException, ExceptionController.EmployeeNoSkillException {
        return employeeService.addEmployee(employee);
    }

    /**
     * Récupère l'Employee de la Database via son Id
     * @param id - L'id de l'Employee à récupérer
     * @return Employee - L'Employee récupéré
     * @throws ExceptionController.EmployeeInvalidIdException - Aucun Employee n'a l'id passé en paramètre
     */
    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable("employeeId") int id) throws ExceptionController.EmployeeInvalidIdException {
        return employeeService.getEmployee(id);
    }

    /**
     * Modifie l'Employee de l'id passé en paramètre par l'Employee passé en paramètre
     * @param id - L'id de l'Employee à modifier
     * @param employee - Le nouvel Employee associé à cet id
     * @return Employee - La nouvelle valeur de l'Employee
     * @throws ExceptionController.SalaryBelowSmicException - L'Employee a un salaire inférieur au SMIC
     * @throws ExceptionController.EmployeeNoNameException - L'Employee n'a pas de nom
     * @throws ExceptionController.EmployeeNoSkillException - L'Employee n'a pas de compétences
     */
    @RequestMapping(value = "employee/{employeeId}", method = RequestMethod.PUT)
    Employee updateEmployee(@PathVariable("employeeId") int id, @RequestBody Employee employee) throws ExceptionController.SalaryBelowSmicException, ExceptionController.EmployeeNoNameException, ExceptionController.EmployeeNoSkillException {
        return employeeService.updateEmployee(id, employee);
    }

    /**
     * Supprime l'Employee associé à l'id passé en paramètre
     * @param id - L'id de l'Employee à supprimer
     * @return Employee - L'Employee supprimé
     * @throws ExceptionController.EmployeeInvalidIdBadReuestException - Aucun Employee n'a l'id passé en paramètre
     */
    @RequestMapping(value = "employee/{employeeId}", method = RequestMethod.DELETE)
    Employee deleteEmployee(@PathVariable("employeeId") int id) throws ExceptionController.EmployeeInvalidIdBadReuestException {
        return employeeService.deleteEmployee(id);
    }

}
