package com.thoughtworks.leanengine.infra.repo.workflow;

import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowInstanceContextPO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkflowInstanceRepository
    extends MongoRepository<WorkflowInstanceContextPO, String> {}
