package com.thoughtworks.leanengine.infra.repo.po.person;

import com.thoughtworks.leanengine.infra.repo.po.PersistenceObject;
import com.thoughtworks.leanengine.personcontext.Person.Person;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "person")
@NoArgsConstructor
public class PersonPO implements PersistenceObject<Person> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String note;

  @Override
  public Person toDomainModel() {
    return new Person(id, name, note);
  }

  public static PersonPO of(Person person) {
    if (person == null) return null;
    return new PersonPO(person.getId(), person.getName(), person.getNote());
  }
}