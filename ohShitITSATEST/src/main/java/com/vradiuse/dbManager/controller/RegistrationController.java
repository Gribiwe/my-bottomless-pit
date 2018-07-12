package com.vradiuse.dbManager.controller;

import com.vradiuse.dbManager.controller.inputData.RegData;
import com.vradiuse.dbManager.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    private PersonRepository personRepository;

    @Autowired
    public RegistrationController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @PutMapping(value = "/registration")
    @ResponseStatus(value = HttpStatus.OK)
    public void registraton(@RequestBody RegData person) {

    }

}
