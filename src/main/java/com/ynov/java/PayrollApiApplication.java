package com.ynov.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author : Mathieu Miramont
 * Date : 13/11/2015
 * TP : https://docs.google.com/document/d/1iiCIDjl3OOKSRynPcTdH7tkNFFRdlN_LfQMIZvSXQ18/edit
 * Fork du repo : https://github.com/micouz/payroll-api-spring
 *
 * Sources / Tutoriels :
 * - Gestion des exceptions : https://dzone.com/articles/exception-handling-spring-rest
 * - @RequestBody et @PathVariable / Controlleurs : http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
 */
@SpringBootApplication
public class PayrollApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayrollApiApplication.class, args);
    }

}
