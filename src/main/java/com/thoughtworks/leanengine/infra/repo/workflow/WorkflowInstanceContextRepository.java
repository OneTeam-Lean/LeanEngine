package com.thoughtworks.leanengine.infra.repo.workflow;

import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowInstanceContextPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowInstanceContextRepository
    extends MongoRepository<WorkflowInstanceContextPO, String> {}
