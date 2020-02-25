package com.thoughtworks.leanengine.adapter;

import static com.google.common.collect.Lists.newArrayList;
import static io.restassured.RestAssured.when;

import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Edge;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Position;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Shape;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Size;
import com.thoughtworks.leanengine.domain.workflowcontext.common.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowControllerTest extends ApiTestBase {

  @Autowired private WorkflowRepository workflowRepository;

  @Test
  void return_workflow_json_when_GET_workflow_api() {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setComponents(
        newArrayList(
            new AutoTask(
                "taskId", "taskName", Status.PENDING, LocalDateTime.now(), LocalDateTime.now())));
    workflowPO.setName("apiTestSave");
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("componentId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("flowId", Position.of(100, 200), Position.of(300, 400))));
    workflowPO.setLanes(newArrayList(new Lane("testLane", "laneId", newArrayList())));
    workflowRepository.save(workflowPO);
    when().get("/workflow/apiTestSave").then().statusCode(200).log().body();
    workflowRepository.deleteByName("apiTestSave");
  }
}
