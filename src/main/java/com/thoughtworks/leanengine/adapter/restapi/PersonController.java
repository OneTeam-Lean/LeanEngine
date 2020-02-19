package com.thoughtworks.leanengine.adapter.restapi;

import com.thoughtworks.leanengine.adapter.dto.PersonDTO;
import com.thoughtworks.leanengine.application.PersonUserCase;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PersonController {

  private final PersonUserCase personUserCase;

  public PersonController(PersonUserCase personUserCase) {
    this.personUserCase = personUserCase;
  }

  @GetMapping("/persons")
  public List<PersonDTO> getPersons() {
    return personUserCase.queryAllPerson();
  }
}
