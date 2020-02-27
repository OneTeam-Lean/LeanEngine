package com.thoughtworks.leanengine.adapter.restapi;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.ManualTask;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SimulatorControllerTest extends ApiTestBase {
  @Autowired private WorkflowRepository repo;

  @Test
  public void shouldModifyComponentsStatus() {
    WorkflowPO workflowPO = buildWorkflowPO("statusTest");
    repo.save(workflowPO);
    when().put("/simulator/statusTest/manualTaskId/SUCCESS").then().statusCode(200);
    WorkflowPO updatedWorkflowPO = repo.findByName("statusTest");
    List<Component> components = updatedWorkflowPO.getComponents();
    components
        .stream()
        .forEach(
            item -> {
              if ("manualTaskId".equalsIgnoreCase(item.getId())) {
                ManualTask manualTask = (ManualTask) item;
                assertEquals(manualTask.getStatus(), Status.SUCCESS);
              }
            });
    repo.deleteByName("statusTest");
  }
}
