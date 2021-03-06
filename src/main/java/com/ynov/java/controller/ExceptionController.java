package com.ynov.java.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mathieu on 13/11/2015.
 * Classe des exceptions personnalisées.
 */
@RestController
public class ExceptionController extends Throwable {
    @ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="Le salaire ne peut être inférieur au SMIC")
    public class SalaryBelowSmicException extends Exception
    {
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="L'employé n'a pas de nom !")
    public class EmployeeNoNameException extends Exception
    {
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Pas d'employé avec cet id")
    public class EmployeeInvalidIdException extends Exception{
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="Pas d'employé avec cet id")
    public class EmployeeInvalidIdBadReuestException extends Exception{
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="L'employé n'a pas de compétences !")
    public class EmployeeNoSkillException extends Exception
    {
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="Le projet n'a pas de nom !")
    public class ProjectNoNameException extends Exception
    {
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Pas de projet avec cet id")
    public class ProjectInvalidIdException extends Exception{
        private static final long serialVersionUID = 100L;
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Pas de projet avec cet id")
    public class ProjectInvalidIdBadRequestException extends Exception{
        private static final long serialVersionUID = 100L;
    }
}
