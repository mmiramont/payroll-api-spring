package com.ynov.java.service;

import com.ynov.java.controller.ExceptionController;
import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.Employee;
import com.ynov.java.model.Project;
import com.ynov.java.model.Skill;

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

    public Project getProject(int id) throws ProjectInvalidId {
        if (projectMap.get(Long.valueOf(id)) == null)
            throw new ProjectInvalidId();
        return projectMap.get(Long.valueOf(id));
    }

    public Project updateProject (int id, Project project){
        project.setId(id);
        projectMap.replace(Long.valueOf(id), project);
        return project;
    }

    public Project deleteProject (int id){
        Project project = projectMap.get(Long.valueOf(id));
        projectMap.remove(Long.valueOf(id));
        return project;
}

    public void addMembertoProject(int projectId, int employeeId) throws EmployeeInvalidId{
        Project project = projectMap.get(Long.valueOf(projectId));
        Employee employee = employeeMap.get(Long.valueOf(employeeId));
        if(employee == null)
            throw new ExceptionController.EmployeeInvalidId();
        project.addEmployeeToTeam(employee);
        projectMap.replace(Long.valueOf(projectId), project);
    }

    public List<Employee> getEmployeeListOfProject(int projectId){
        return projectMap.get(Long.valueOf(projectId)).getTeam();
    }

    public List<Skill> fulfilledSkills(int projectId) {
        Project project =  projectMap.get(Long.valueOf(projectId));
        List<Skill> skills = project.getRequiredSkills();
        List<Skill> valide = new ArrayList<>();
        for(Employee e : project.getTeam()){
            for(Skill skill : e.getSkills()){
                if (skills.contains(skill))
                        valide.add(skill);
            }
        }
        return valide;
    }

    public List<Skill> unfulfilledSkills(int projectId){
        Project project =  projectMap.get(Long.valueOf(projectId));
        List<Skill> skills = project.getRequiredSkills();
        List<Skill> fulfilled = fulfilledSkills(projectId);
        skills.removeAll(fulfilled);
        return skills;
    }
}
