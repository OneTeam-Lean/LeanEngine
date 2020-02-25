package com.thoughtworks.leanengine.infra.repo.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowRepositoryTest extends ApiTestBase {

  @Autowired private WorkflowRepository repo;

  @AfterEach
  public void cleanDB() {
    repo.findAll()
        .stream()
        .filter(po -> po.getName().toLowerCase().contains("test"))
        .forEach(po -> repo.deleteByName(po.getName()));
  }

  @Test
  void should_return_1_when_save_workflowPO() {
    WorkflowPO workflowPO = buildWorkflowPO("testSave");
    repo.save(workflowPO);
    WorkflowPO example = new WorkflowPO();
    example.setName("testSave");
    assertEquals(1, repo.findAll().stream().filter(po -> po.getName().equals("testSave")).count());
    repo.deleteByName("testSave");
  }

  @Test
  void should_return_1_when_delete_saved_PO() {
    WorkflowPO workflowPO = buildWorkflowPO("testSave");
    repo.save(workflowPO);
    Long cnt = repo.deleteByName("testSave");
    assertTrue(cnt > 0);
    WorkflowPO byName = repo.findByName("testSave");
    assertNull(byName);
  }

  @Test
  void should_return_workflowPO_when_find_by_name_testFind() throws JsonProcessingException {
    WorkflowPO workflowPO = buildWorkflowPO("testFind");
    repo.save(workflowPO);
    WorkflowPO testFind = repo.findByName("testFind");
    assertEquals(
        objectMapper.writeValueAsString(testFind), objectMapper.writeValueAsString(workflowPO));
    repo.deleteByName("testFind");
  }

  @Test
  void return_workflowPO_name_changed_updateTest_when_update_workflowPO_name_to_updateTest()
      throws JsonProcessingException {
    WorkflowPO workflowPO = buildWorkflowPO("testFind");
    repo.save(workflowPO);
    WorkflowPO testUpdate = repo.findByName("testFind");
    testUpdate.setName("updateTest");
    repo.save(testUpdate);
    WorkflowPO updateTest = repo.findByName("updateTest");
    assertNotNull(updateTest);
    assertEquals(updateTest.getName(), "updateTest");
    updateTest.setName("testFind");
    assertEquals(
        objectMapper.writeValueAsString(updateTest), objectMapper.writeValueAsString(workflowPO));
  }
}
