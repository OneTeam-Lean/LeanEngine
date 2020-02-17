package com.thoughtworks.leanengine.infra.repo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.thoughtworks.leanengine.JpaTestBase;
import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO;
import com.thoughtworks.leanengine.personcontext.Person.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonRepositoryJpaTest extends JpaTestBase {

  @Autowired private PersonRepository personRepo;

  @Test
  public void return_1_when_insert_person() {
    Person person = new Person("test1", "note1");
    long originCnt = personRepo.count();
    personRepo.save(PersonPO.of(person));
    long savedCnt = personRepo.count();
    assertThat(savedCnt - originCnt, equalTo(1L));
  }
}
