package com.ynov.java.service;

import com.ynov.java.controller.ExceptionController;
import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mathieu on 12/11/2015.
 * Classe gérant toute l'exécution des routes appelées pour la classe Project
 */
public class ProjectService extends ExceptionController{
    private static Map<Long, Project> projectMap = DatabaseClass.getProjects();
    private static Map<Long, Employee> employeeMap = DatabaseClass.getMessages();

    /**
     * Ajoute un projet à la Database
     * @param project Project - le projet à rajouter à la Database
     * @return Project - le projet enregistré avec son id
     * @throws ProjectNoNameException - Le projet n'a pas de nom
     */
    public Project addProject(Project project) throws ProjectNoNameException {
        checkProject(project);
        project.setId(projectMap.size()+1);
        projectMap.put(Long.valueOf(projectMap.size())+1, project);
        return project;
    }

    /**
     * Récupère le Project de la Database via son Id
     * @param id - L'id du Project à récupérer
     * @return Project - Le Project récupéré
     * @throws ProjectInvalidIdException - Aucun Project n'a cet id
     */
    public Project getProject(int id) throws ProjectInvalidIdException {
        if (projectMap.get(Long.valueOf(id)) == null)
            throw new ProjectInvalidIdException();
        return projectMap.get(Long.valueOf(id));
    }

    /**
     * Modifie le Project de l'id passé en paramètre par le Project passé en paramètre
     * @param id - L'id du Project à modifier
     * @param project Project - Le nouveau Project
     * @return Project - Le nouveau Project
     * @throws ProjectNoNameException - Le nouveau Project n'a pas de nom
     */
    public Project updateProject (int id, Project project) throws ProjectNoNameException {
        checkProject(project);
        project.setId(id);
        projectMap.replace(Long.valueOf(id), project);
        return project;
    }

    /**
     * Supprime le Project dont l'id est passé en paramètre
     * @param id - l'id du Project à supprimer
     * @return Project - Le Project supprimé
     * @throws ProjectInvalidIdBadRequestException - Aucun Project n'a cet id
     */
    public Project deleteProject (int id) throws ProjectInvalidIdBadRequestException {
        Project project = projectMap.get(Long.valueOf(id));
        if (project == null)
            throw new ProjectInvalidIdBadRequestException();
        projectMap.remove(Long.valueOf(id));
        return project;
}

    /**
     *
     * @param projectId - L'id du Project
     * @param employeeId - L'id de l'Employee à rajouter au Project
     * @throws EmployeeInvalidIdException - Aucun Employee n'a cet id
     */
    public void addMembertoProject(int projectId, int employeeId) throws EmployeeInvalidIdException {
        // On récupère le Project et l'Employee
        Project project = projectMap.get(Long.valueOf(projectId));
        Employee employee = employeeMap.get(Long.valueOf(employeeId));
        if(employee == null)
            throw new EmployeeInvalidIdException();
        // On ajoute l'Employee au Project et on le met à jour dans la liste des Project
        project.addEmployeeToTeam(employee);
        projectMap.replace(Long.valueOf(projectId), project);
    }

    /**
     * * Récupère la liste des Employee associé au Project dont l'id est fourni en paramètre
     * @param projectId - L'id du Project
     * @return La liste des Employee du Project
     */
    public List<Employee> getEmployeeListOfProject(int projectId){
        return projectMap.get(Long.valueOf(projectId)).getTeam();
    }

    /**
     * Récupère la liste des compétences du projet que les membres de l'équipe possèdent
     * @param projectId - l'id du Project
     * @return La liste des compétences du projet que les membres de l'équipe possèdent dans un objet "skills"
     */
    public List<Skill> fulfilledSkills(int projectId) {
        Project project =  projectMap.get(Long.valueOf(projectId));
        List<Skill> skills = project.getRequiredSkills();

        // Une liste vide qui contiendra les compétences que les membres de l'équipe possèdent
        List<Skill> valide = new ArrayList<>();
        // Pour chaque Employee, on regarde si chacun de ses Skill est contenu dans la liste des Skill du projet.
        // Si oui, on les ajoute dans la liste qui sera retournée
        for(Employee e : project.getTeam()){
            valide.addAll(e.getSkills().stream().filter(skill -> skills.contains(skill)).collect(Collectors.toList()));
        }
        return valide;
    }

    /**
     * Récupère la liste des compétences du projet que les membres de l'équipe ne possèdent pas
     * @param projectId - l'id du Project
     * @return La liste des compétences du projet que les membres de l'équipe ne possèdent pas dans un objet "skills"
     */
    public List<Skill> unfulfilledSkills(int projectId){
        Project project =  projectMap.get(Long.valueOf(projectId));
        List<Skill> skills = project.getRequiredSkills();
        List<Skill> fulfilled = fulfilledSkills(projectId);
        // On inverse la liste des Skill
        skills.removeAll(fulfilled);
        return skills;
    }

    /**
     * Vérifie les valeurs du Project
     * @param project - Le Project à vérifier
     * @throws ProjectNoNameException - Le Project n'a pas de nom
     */
    private void checkProject(Project project) throws ProjectNoNameException {
        if (project.getName() == "" || project.getName() == null)
            throw new ProjectNoNameException();
    }
}
