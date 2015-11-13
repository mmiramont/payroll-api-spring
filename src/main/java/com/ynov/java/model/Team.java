package com.ynov.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 13/11/2015.
 */
public class Team {
    public List<Employee> team = new ArrayList<>();

    public Team(List<Employee> team) {
        this.team = team;
    }

    public Team() {

    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }

    public void add(Employee employee) {
        team.add(employee);
    }
}
