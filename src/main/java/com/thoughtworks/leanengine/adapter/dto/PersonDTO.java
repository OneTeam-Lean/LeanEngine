package com.thoughtworks.leanengine.adapter.dto;

import com.thoughtworks.leanengine.adapter.DataTransferObject;
import com.thoughtworks.leanengine.domain.personcontext.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO implements DataTransferObject {
  private Long id;
  private String name;

  public static PersonDTO of(Person person) {
    return new PersonDTO(person.getId(), person.getName());
  }
}
