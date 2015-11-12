package com.ynov.java.controller;

import com.ynov.java.model.Employee;
import com.ynov.java.model.Project;
import com.ynov.java.model.Skill;
import com.ynov.java.service.ProjectService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by Mathieu on 12/11/2015.
 */
@RestController
@EnableAutoConfiguration
public class ProjectController {

    ProjectService projectService = new ProjectService();

    @RequestMapping(value = "project", method = RequestMethod.POST)
    Project addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @RequestMapping(value = "project/{projectId}", method = RequestMethod.GET)
    Project getProject(@PathVariable("projectId") int id){
        return projectService.getProject(id);
    }

    @RequestMapping(value = "project/{projectId}", method = RequestMethod.PUT)
    Project updateEmployee(@PathVariable("projectId") int id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }

    @RequestMapping(value = "project/{projectId}", method = RequestMethod.DELETE)
    Project deleteEmployee(@PathVariable("projectId") int id){
        return projectService.deleteProject(id);
    }

    @RequestMapping(value = "project/{projectId}/teamMember", method = RequestMethod.POST)
    void addEmployeeToProject(@PathVariable("projectId") int projectId, @RequestBody Project project){
        projectService.addMembertoProject(projectId, project.getEmployeeId());
    }

    @RequestMapping(value = "project/{projectId}/teamMember/list", method = RequestMethod.GET)
    List<Employee> getEmployeeListOfProject(@PathVariable("projectId") int projectId){
        return projectService.getEmployeeListOfProject(projectId);
    }

    @RequestMapping(value = "project/{projectId}/fulfilledSkills", method = RequestMethod.GET)
    List<Skill> fulfilledSkills(@PathVariable("projectId") int projectId){
        return projectService.fulfilledSkills(projectId);
    }

    @RequestMapping(value = "project/{projectId}/unfilledSkills", method = RequestMethod.GET)
    List<Skill> unfilledSkills(@PathVariable("projectId") int projectId){
        return projectService.unfulfilledSkills(projectId);
    }

}
