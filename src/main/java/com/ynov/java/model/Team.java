package com.ynov.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 13/11/2015.
 * Classe utilisée pour renvoyer les liste des Employee d'un Project dans un objet "team"
 */
public class Team {
    public List<Employee> team = new ArrayList<>();

    public Team(List<Employee> team) {
        this.team = team;
    }
}
