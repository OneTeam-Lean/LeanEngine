package com.thoughtworks.leanengine.adapter.restapi;

import com.thoughtworks.leanengine.domain.personcontext.person.Person;
import com.thoughtworks.leanengine.domain.personcontext.person.PersonService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/persons")
  public List<Person> getPersons() {
    return personService.queryAllPersons();
  }
}
