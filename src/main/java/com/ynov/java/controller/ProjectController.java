package com.ynov.java.controller;

import com.ynov.java.model.Project;
import com.ynov.java.model.SkillList;
import com.ynov.java.model.Team;
import com.ynov.java.service.ProjectService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mathieu on 12/11/2015.
 * Controlleur de la classe Project
 * Contient toutes les routes de la classe
 * Chaque route passe par une méthode du service associé
 */
@RestController
@EnableAutoConfiguration
public class ProjectController {

    ProjectService projectService = new ProjectService();

    /**
     * Route appelée en POST pour enregistrer un Project
     * @param project Project - Le projet à enregistrer
     * @return Project - Le Project enregistré avec le statut HTTP 201 CREATED
     * @throws ExceptionController.ProjectNoNameException - Le Project n'a pas de nom
     */
    @RequestMapping(value = "project", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Project addProject(@RequestBody Project project) throws ExceptionController.ProjectNoNameException {
        return projectService.addProject(project);
    }

    /**
     * Récupère le Project de la Database via son Id
     * @param id - L'id du projet à récupérer
     * @return Project - Le projet récupéré
     * @throws ExceptionController.ProjectInvalidIdException - Aucun Project ne possède cet id
     */
    @RequestMapping(value = "project/{projectId}", method = RequestMethod.GET)
    Project getProject(@PathVariable("projectId") int id) throws ExceptionController.ProjectInvalidIdException {
        return projectService.getProject(id);
    }

    /**
     * Modifie le Project de l'id passé en paramètre par le Project passé en paramètre
     * @param project Project - Le projet à enregistrer
     * @return Project - Le Project enregistré avec le statut HTTP 201 CREATED
     * @throws ExceptionController.ProjectNoNameException - Le Project n'a pas de nom
     */
    @RequestMapping(value = "project/{projectId}", method = RequestMethod.PUT)
    Project updateEmployee(@PathVariable("projectId") int id, @RequestBody Project project) throws ExceptionController.ProjectNoNameException {
        return projectService.updateProject(id, project);
    }

    /**
     * Supprime le Project dont l'id est passé en paramètre
     * @param id - L'id du Project à supprimer
     * @return Project - le Project supprimé
     * @throws ExceptionController.ProjectInvalidIdBadRequestException - Aucun Project ne possède cet id
     */
    @RequestMapping(value = "project/{projectId}", method = RequestMethod.DELETE)
    Project deleteEmployee(@PathVariable("projectId") int id) throws ExceptionController.ProjectInvalidIdBadRequestException {
        return projectService.deleteProject(id);
    }

    /**
     * Ajoute l'Employee fourni par son id au Project fourni par son Id
     * @param projectId - L'id du Project auquel l'Employee sera rajouté
     * @param project Project - Un Project contenant l'objet "employeeId"
     * @throws ExceptionController.EmployeeInvalidIdException - Aucun Employee possède cet id
     */
    @RequestMapping(value = "project/{projectId}/teamMember", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    void addEmployeeToProject(@PathVariable("projectId") int projectId, @RequestBody Project project) throws ExceptionController.EmployeeInvalidIdException {
        projectService.addMembertoProject(projectId, project.getEmployeeId());
    }

    /**
     * Récupère la liste des Employee associé au Project dont l'id est fourni en paramètre
     * @param projectId - l'id du Project
     * @return Team - L'équipe (liste d'Employee) lié au Project, dans un objet "team"
     */
    @RequestMapping(value = "project/{projectId}/teamMember/list", method = RequestMethod.GET)
    Team getEmployeeListOfProject(@PathVariable("projectId") int projectId){
        return new Team(projectService.getEmployeeListOfProject(projectId));
    }

    /**
     * Récupère la liste des compétences du projet que les membres de l'équipe possèdent
     * @param projectId - l'id du Project
     * @return La liste des compétences du projet que les membres de l'équipe possèdent dans un objet "skills"
     */
    @RequestMapping(value = "project/{projectId}/fulfilledSkills", method = RequestMethod.GET)
    SkillList fulfilledSkills(@PathVariable("projectId") int projectId){
        return new SkillList(projectService.fulfilledSkills(projectId));
    }

    /**
     * Récupère la liste des compétences du projet que les membres de l'équipe ne possèdent pas
     * @param projectId - l'id du Project
     * @return La liste des compétences du projet que les membres de l'équipe ne possèdent pas dans un objet "skills"
     */
    @RequestMapping(value = "project/{projectId}/unfulfilledSkills", method = RequestMethod.GET)
    SkillList unfilledSkills(@PathVariable("projectId") int projectId){
        return new SkillList(projectService.unfulfilledSkills(projectId));
    }

}
