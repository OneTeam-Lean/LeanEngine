package com.thoughtworks.leanengine.infra.repo.workflow;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Edge;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Position;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Shape;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Size;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowRepositoryTest extends ApiTestBase {

  @Autowired private WorkflowRepository repo;

  @Test
  void should_return_1_when_save_workflowPO() {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setComponents(
        newArrayList(
            new AutoTask(
                "taskId", "taskName", Status.PENDING, LocalDateTime.now(), LocalDateTime.now())));
    workflowPO.setName("testSave");
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("componentId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("flowId", Position.of(100, 200), Position.of(300, 400))));
    workflowPO.setLanes(newArrayList(new Lane("testLane", "laneId", newArrayList())));
    repo.save(workflowPO);
    WorkflowPO example = new WorkflowPO();
    example.setName("testSave");
    assertEquals(1, repo.findAll().stream().filter(po -> po.getName().equals("testSave")).count());
    repo.deleteByName("testSave");
  }

  @Test
  void should_return_1_when_delete_saved_PO() {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setComponents(
        newArrayList(
            new AutoTask(
                "taskId", "taskName", Status.PENDING, LocalDateTime.now(), LocalDateTime.now())));
    workflowPO.setName("testSave");
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("componentId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("flowId", Position.of(100, 200), Position.of(300, 400))));
    workflowPO.setLanes(newArrayList(new Lane("testLane", "laneId", newArrayList())));
    repo.save(workflowPO);
    Long cnt = repo.deleteByName("testSave");
    assertTrue(cnt > 0);
    WorkflowPO byName = repo.findByName("testSave");
    assertNull(byName);
  }

  @Test
  void should_return_workflowPO_when_find_by_name_testFind() {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setWorkflowId("testId");
    workflowPO.setComponents(
        newArrayList(
            new AutoTask(
                "taskId", "taskName", Status.PENDING, LocalDateTime.now(), LocalDateTime.now())));
    workflowPO.setName("testFind");
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("componentId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("flowId", Position.of(100, 200), Position.of(300, 400))));
    workflowPO.setLanes(newArrayList(new Lane("testLane", "laneId", newArrayList())));
    repo.save(workflowPO);
    WorkflowPO testFind = repo.findByName("testFind");
    assertEquals(testFind.toString(), workflowPO.toString());
    repo.deleteByName("testFind");
  }

  @Test
  void return_workflowPO_name_changed_updateTest_when_update_workflowPO_name_to_updateTest() {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setWorkflowId("testId");
    workflowPO.setComponents(
        newArrayList(
            new AutoTask(
                "taskId", "taskName", Status.PENDING, LocalDateTime.now(), LocalDateTime.now())));
    workflowPO.setName("testFind");
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("componentId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("flowId", Position.of(100, 200), Position.of(300, 400))));
    workflowPO.setLanes(newArrayList(new Lane("testLane", "laneId", newArrayList())));
    repo.save(workflowPO);
    WorkflowPO testUpdate = repo.findByName("testFind");
    testUpdate.setName("updateTest");
    repo.save(testUpdate);
    WorkflowPO updateTest = repo.findByName("updateTest");
    assertNotNull(updateTest);
    assertEquals(updateTest.getName(), "updateTest");
    updateTest.setName("testFind");
    assertEquals(updateTest.toString(), workflowPO.toString());
  }
}
