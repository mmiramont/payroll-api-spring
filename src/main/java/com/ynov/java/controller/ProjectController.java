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
 */
@RestController
@EnableAutoConfiguration
public class ProjectController {

    ProjectService projectService = new ProjectService();

    @RequestMapping(value = "project", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Project addProject(@RequestBody Project project) throws ExceptionController.ProjectNoNameException {
        return projectService.addProject(project);
    }

    @RequestMapping(value = "project/{projectId}", method = RequestMethod.GET)
    Project getProject(@PathVariable("projectId") int id) throws ExceptionController.ProjectInvalidIdException {
        return projectService.getProject(id);
    }

    @RequestMapping(value = "project/{projectId}", method = RequestMethod.PUT)
    Project updateEmployee(@PathVariable("projectId") int id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }

    @RequestMapping(value = "project/{projectId}", method = RequestMethod.DELETE)
    Project deleteEmployee(@PathVariable("projectId") int id) throws ExceptionController.ProjectInvalidIdBadRequestException {
        return projectService.deleteProject(id);
    }

    @RequestMapping(value = "project/{projectId}/teamMember", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    void addEmployeeToProject(@PathVariable("projectId") int projectId, @RequestBody Project project) throws ExceptionController.EmployeeInvalidIdException {
        projectService.addMembertoProject(projectId, project.getEmployeeId());
    }

    @RequestMapping(value = "project/{projectId}/teamMember/list", method = RequestMethod.GET)
    Team getEmployeeListOfProject(@PathVariable("projectId") int projectId){
        return projectService.getEmployeeListOfProject(projectId);
    }

    @RequestMapping(value = "project/{projectId}/fulfilledSkills", method = RequestMethod.GET)
    SkillList fulfilledSkills(@PathVariable("projectId") int projectId){
        return projectService.fulfilledSkills(projectId);
    }

    @RequestMapping(value = "project/{projectId}/unfulfilledSkills", method = RequestMethod.GET)
    SkillList unfilledSkills(@PathVariable("projectId") int projectId){
        return projectService.unfulfilledSkills(projectId);
    }

}
