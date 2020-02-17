package com.thoughtworks.leanengine.domain.personcontext.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {
  private Long id;
  private String name;
  private String note;

  public Person(String name, String note) {
    this.name = name;
    this.note = note;
  }
}
