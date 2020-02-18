package com.thoughtworks.leanengine.infra.repo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.thoughtworks.leanengine.domain.personcontext.person.Person;
import com.thoughtworks.leanengine.infra.JpaTestBase;
import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseSetup("classpath:data/person.xml")
@DatabaseTearDown
public class PersonRepositoryJpaTest extends JpaTestBase {

  @Autowired private PersonRepository personRepo;

  @Test
  public void return_3_size_when_count_person() {
    long allCnt = personRepo.count();
    assertThat(allCnt, is(3L));
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
    List<PersonPO> all = personRepo.findAll();
    assertThat(all.size(), is(3));
  }

  @Test
  public void return_2_persons_when_delete_one() {
    List<PersonPO> personPOS = personRepo.findAll();
    Long needDeleteId = personPOS.get(1).getId();
    personRepo.deleteById(needDeleteId);
    List<PersonPO> deletedPersons = personRepo.findAll();
    assertThat(deletedPersons.size(), is(2));
  }

  @Test
  public void return_name_is_changed_when_save_person_change_name() {
    List<PersonPO> personPOS = personRepo.findAll();
    PersonPO originPerson = personPOS.get(1);
    PersonPO changedPerson = new PersonPO(originPerson.getId(), "changed", originPerson.getNote());
    personRepo.save(changedPerson);
    Optional<PersonPO> personById = personRepo.findById(changedPerson.getId());
    assertTrue(personById.isPresent());
    PersonPO personPO = personById.get();
    assertThat(personPO.getName(), is("changed"));
    assertThat(personPO.getNote(), is("note1"));
    assertThat(personRepo.count(), is(3L));
  }
}
