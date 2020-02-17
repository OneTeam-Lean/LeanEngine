package com.thoughtworks.leanengine.infra.repo;

import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonPO, Long> {}
