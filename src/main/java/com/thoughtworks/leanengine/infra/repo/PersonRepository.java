package com.thoughtworks.leanengine.infra.repo;

import com.thoughtworks.leanengine.infra.repo.po.person.PersonPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonPO, Long> {}
