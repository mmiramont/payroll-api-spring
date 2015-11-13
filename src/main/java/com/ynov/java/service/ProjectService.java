package com.ynov.java.service;

import com.ynov.java.controller.ExceptionController;
import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mathieu on 12/11/2015.
 */
public class ProjectService extends ExceptionController{
    private static Map<Long, Project> projectMap = DatabaseClass.getProjects();
    private static Map<Long, Employee> employeeMap = DatabaseClass.getMessages();

    public Project addProject(Project project) throws ProjectNoNameException {
        if (project.getName() == "" || project.getName() == null)
            throw new ProjectNoNameException();
        project.setId(projectMap.size()+1);
        projectMap.put(Long.valueOf(projectMap.size())+1, project);
        return project;
    }

    public Project getProject(int id) throws ProjectInvalidIdException {
        if (projectMap.get(Long.valueOf(id)) == null)
            throw new ProjectInvalidIdException();
        return projectMap.get(Long.valueOf(id));
    }

    public Project updateProject (int id, Project project){
        project.setId(id);
        projectMap.replace(Long.valueOf(id), project);
        return project;
    }

    public Project deleteProject (int id) throws ProjectInvalidIdBadRequestException {
        Project project = projectMap.get(Long.valueOf(id));
        if (project == null)
            throw new ProjectInvalidIdBadRequestException();
        projectMap.remove(Long.valueOf(id));
        return project;
}

    public void addMembertoProject(int projectId, int employeeId) throws EmployeeInvalidIdException {
        Project project = projectMap.get(Long.valueOf(projectId));
        Employee employee = employeeMap.get(Long.valueOf(employeeId));
        if(employee == null)
            throw new EmployeeInvalidIdException();
        project.addEmployeeToTeam(employee);
        projectMap.replace(Long.valueOf(projectId), project);
    }

    public Team getEmployeeListOfProject(int projectId){
        return projectMap.get(Long.valueOf(projectId)).getTeam();
    }

    public SkillList fulfilledSkills(int projectId) {
        Project project =  projectMap.get(Long.valueOf(projectId));
        List<Skill> skills = project.getRequiredSkills();
        List<Skill> valide = new ArrayList<>();
        for(Employee e : project.getTeam().getTeam()){
            for(Skill skill : e.getSkills()){
                if (skills.contains(skill))
                        valide.add(skill);
            }
        }
        return new SkillList(valide);
    }

    public SkillList unfulfilledSkills(int projectId){
        Project project =  projectMap.get(Long.valueOf(projectId));
        List<Skill> skills = project.getRequiredSkills();
        List<Skill> fulfilled = fulfilledSkills(projectId).getSkills();
        skills.removeAll(fulfilled);
        return new SkillList(skills);
    }
}
