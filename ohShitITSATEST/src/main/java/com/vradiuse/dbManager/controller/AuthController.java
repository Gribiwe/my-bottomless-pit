package com.vradiuse.dbManager.controller;

import com.vradiuse.dbManager.controller.inputData.AuthData;
import com.vradiuse.dbManager.controller.inputData.RegData;
import com.vradiuse.dbManager.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AuthController {

    private PersonRepository personRepository;

    @Autowired
    public AuthController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(value = "/auth")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean isValid(@RequestBody AuthData data) {
        return personRepository.findPersonByLogin(data.getLogin()).getPassword().equals(data.getPassword());
    }

}
