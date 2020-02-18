package com.thoughtworks.leanengine.domain.personcontext.person;

import com.thoughtworks.leanengine.infra.repo.PersonRepository;
import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  private final PersonRepository personRepository;

  public List<Person> queryAllPersons() {
    List<PersonPO> all = personRepository.findAll();
    return all.stream().map(PersonPO::toDomainModel).collect(Collectors.toList());
  }
}
