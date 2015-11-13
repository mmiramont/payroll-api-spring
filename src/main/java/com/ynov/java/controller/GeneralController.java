package com.ynov.java.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mathieu on 13/11/2015.
 * Routes de l'application qui ne concernent pas un modèle de l'application
 */
@RestController
@EnableAutoConfiguration
public class GeneralController {

    /**
     * Retourne le nom de l'auteur de l'API
     * @return Le nom de l'auteur de l'API
     */
    @RequestMapping(value = "author", method = RequestMethod.GET)
    String author(){
        return "{\"name\" : \"Mathieu Miramont\"}";
    }
}
