package com.thoughtworks.leanengine.adapter.restapi;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowControllerTest extends ApiTestBase {

  @Autowired private WorkflowRepository repo;

  @Test
  void return_workflow_json_when_GET_workflow_api() {
    WorkflowPO workflowPO = buildWorkflowPO("apiTestSave");
    repo.save(workflowPO);
    when().get("/workflow/apiTestSave").then().statusCode(200).log().all();
  }

  @Test
  void return_workflow_in_mongo_when_POST_workflow() throws JsonProcessingException {
    given()
        .contentType(ContentType.JSON)
        .body(getWorkflowJson("postTest"))
        .post("/workflow")
        .then()
        .statusCode(200)
        .log()
        .all();
    WorkflowPO workflowPO = repo.findByName("postTest");
    assertNotNull(workflowPO);
  }

  @Test
  void return_autoTask_name_testTask_when_PUT_workflow() throws JsonProcessingException {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setId("testWorkflowId");
    workflowPO.setName("putTest");
    repo.save(workflowPO);
    WorkflowPO putTest = buildWorkflowPO("putTest");
    putTest.setId("testWorkflowId");
    given()
        .contentType(ContentType.JSON)
        .body(objectMapper.writeValueAsString(putTest))
        .put("/workflow")
        .then()
        .statusCode(200)
        .log()
        .all();
    WorkflowPO updatedPO = repo.findByName("putTest");
    assertNotNull(updatedPO.getId());
    assertNotNull(updatedPO.getComponents());
    assertNotNull(updatedPO.getDiagrams());
    assertNotNull(updatedPO.getLanes());
  }
}
