package com.thoughtworks.leanengine;

import static com.google.common.collect.Lists.newArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.leanengine.domain.workflowcontext.containers.Lane;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Edge;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Position;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Shape;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Size;
import com.thoughtworks.leanengine.domain.workflowcontext.events.EndEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.events.StartEvent;
import com.thoughtworks.leanengine.domain.workflowcontext.flows.SequenceFlow;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.AutoTask;
import com.thoughtworks.leanengine.domain.workflowcontext.tasks.ManualTask;
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApiTestBase {
  @LocalServerPort private int port;

  @Autowired protected ObjectMapper objectMapper;
  @Autowired protected WorkflowRepository repo;

  @BeforeEach
  void setUp() {
    RestAssured.basePath = "/";
    RestAssured.port = port;
    RestAssured.requestSpecification =
        new RequestSpecBuilder().setContentType("application/json").build();

    RestAssured.config =
        RestAssuredConfig.config()
            .objectMapperConfig(
                new ObjectMapperConfig()
                    .jackson2ObjectMapperFactory((cls, charset) -> objectMapper));
  }

  @AfterEach
  void cleanUp() {
    repo.deleteAll();
  }

  protected WorkflowPO buildWorkflowPO(String name) {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setComponents(
        newArrayList(
            new StartEvent("startEventId", "startEvent"),
            new SequenceFlow("sequenceFlowId_1", "startEventId", "autoTaskId"),
            new AutoTask("autoTaskId", "taskName", LocalDateTime.now(), LocalDateTime.now()),
            new SequenceFlow("sequenceFlowId_2", "autoTaskId", "manualTaskId"),
            new ManualTask(
                "manualTaskId", "manualTaskName", LocalDateTime.now(), LocalDateTime.now()),
            new SequenceFlow("sequenceFlowId_3", "manualTaskId", "endEventId"),
            new EndEvent("endEventId", "endEvent")));
    workflowPO.setName(name);
    workflowPO.setDiagrams(
        newArrayList(
            Shape.of("startEventId", Size.of(5, 10), Position.of(30, 40)),
            Edge.of("sequenceFlowId_1", 1, 2),
            Shape.of("autoTaskId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("sequenceFlowId_2", 1, 3),
            Shape.of("autoTaskId", Size.of(10, 20), Position.of(30, 40)),
            Edge.of("sequenceFlowId_3", 4, 1),
            Shape.of("manualTaskId", Size.of(10, 20), Position.of(30, 40)),
            Shape.of("endEventId", Size.of(5, 10), Position.of(30, 40))));
    workflowPO.setLanes(
        newArrayList(
            new Lane(
                "testLane",
                "laneId",
                newArrayList(
                    "startEventId",
                    "sequenceFlowId_1",
                    "autoTaskId",
                    "sequenceFlowId_2",
                    "manualTaskId",
                    "sequenceFlowId_3",
                    "endEventId"))));
    return workflowPO;
  }

  protected String getWorkflowJson(String workflowName) throws JsonProcessingException {
    Workflow workflow =
        new Workflow(
            workflowName, null, newArrayList(), newArrayList(), newArrayList(), newArrayList());
    return objectMapper.writeValueAsString(workflow);
  }

  @Test
  void print_full_dsl_json() throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(buildWorkflowPO("workflowName"));
    System.out.println(json);
  }
}
