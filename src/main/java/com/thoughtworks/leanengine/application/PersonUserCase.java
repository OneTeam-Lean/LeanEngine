package com.thoughtworks.leanengine.application;

import com.thoughtworks.leanengine.adapter.dto.PersonDTO;
import com.thoughtworks.leanengine.domain.personcontext.person.PersonService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PersonUserCase {
  private final PersonService personService;

  public PersonUserCase(PersonService personService) {
    this.personService = personService;
  }

  public List<PersonDTO> queryAllPerson() {
    return personService.queryAllPersons().stream().map(PersonDTO::of).collect(Collectors.toList());
  }
}
