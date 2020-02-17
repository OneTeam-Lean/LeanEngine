package com.thoughtworks.leanengine.infra.repo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.thoughtworks.leanengine.JpaTestBase;
import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO;
import com.thoughtworks.leanengine.personcontext.Person.Person;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonRepositoryJpaTest extends JpaTestBase {

  @Autowired private PersonRepository personRepo;

  @BeforeEach
  public void prepareData() {
    Person person1 = new Person("test1", "note1");
    Person person2 = new Person("test1", "note1");
    Person person3 = new Person("test1", "note1");
    personRepo.save(PersonPO.of(person1));
    personRepo.save(PersonPO.of(person2));
    personRepo.save(PersonPO.of(person3));
  }

  @Test
  public void return_1_when_insert_person() {
    Person person = new Person("test1", "note1");
    long originCnt = personRepo.count();
    personRepo.save(PersonPO.of(person));
    long savedCnt = personRepo.count();
    assertThat(savedCnt - originCnt, equalTo(1L));
  }

  @Test
  public void return_3_persons_when_find_all() {
    List<PersonPO> all = (List<PersonPO>) personRepo.findAll();
    assertThat(all.size(), is(3));
  }
}
