package com.thoughtworks.leanengine.infra.repo.workflow;

import static com.google.common.collect.Lists.newArrayList;

import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Edge;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Position;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Shape;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Size;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowRepositoryTest extends ApiTestBase {

  @Autowired private WorkflowRepository repo;

  @Test
  void should_return_1_when_save_workflowPO() {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setComponents(newArrayList(new AutoTask()));
    workflowPO.setName("testSave");
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("componentId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("flowId", Position.of(100, 200), Position.of(300, 400))));
    workflowPO.setLanes(newArrayList(new Lane("testLane", "laneId", newArrayList())));
    repo.save(workflowPO);
  }
}
