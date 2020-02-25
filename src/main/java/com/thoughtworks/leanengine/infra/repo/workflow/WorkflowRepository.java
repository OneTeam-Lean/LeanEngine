package com.thoughtworks.leanengine.infra.repo.workflow;

import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends MongoRepository<WorkflowPO, String> {
  WorkflowPO findByName(String name);

  Long deleteByName(String name);
}
