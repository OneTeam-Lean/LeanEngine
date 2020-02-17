package com.thoughtworks.leanengine.domain.personcontext.person

import com.thoughtworks.leanengine.infra.repo.PersonRepository
import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO
import spock.lang.Specification

class PersonServiceTest extends Specification {
    private PersonService personService
    private PersonRepository personRepository

    void setup() {
        personRepository = Stub(PersonRepository.class)
        personService = new PersonService(personRepository)
    }

    def "return 3 persons when find all person"() {
        given:
        def personList = new ArrayList<>()
        personList.add(new PersonPO(1, "person1", "note"))
        personList.add(new PersonPO(2, "person2", "note"))
        personList.add(new PersonPO(2, "person3", "note"))
        personRepository.findAll() >> personList
        when:
        def persons = personService.queryAllPersons()
        then:
        persons.size == 3
        persons.every{
            it.note== 'note'
        }
        persons.name << ['person1','person2','person3']


    }
}
