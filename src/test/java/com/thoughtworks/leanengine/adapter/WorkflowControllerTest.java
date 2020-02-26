package com.thoughtworks.leanengine.adapter;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowControllerTest extends ApiTestBase {

  @Autowired private WorkflowRepository repo;

  @AfterEach
  public void cleanDB() {
    repo.findAll()
        .stream()
        .filter(po -> po.getName().toLowerCase().contains("test"))
        .forEach(po -> repo.deleteByName(po.getName()));
  }

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
    repo.deleteByName("postTest");
  }

  @Test
  void return_autoTask_name_testTask_when_PUT_workflow() throws JsonProcessingException {
    WorkflowPO workflowPO = new WorkflowPO();
    workflowPO.setName("putTest");
    WorkflowPO save = repo.save(workflowPO);
    WorkflowPO putTest = buildWorkflowPO("putTest");
    putTest.setWorkflowId(save.getWorkflowId());
    given()
        .contentType(ContentType.JSON)
        .body(objectMapper.writeValueAsString(putTest))
        .put("/workflow")
        .then()
        .statusCode(200)
        .log()
        .all();
    WorkflowPO updatedPO = repo.findByName("putTest");
    assertNotNull(updatedPO.getWorkflowId());
    assertNotNull(updatedPO.getComponents());
    assertNotNull(updatedPO.getDiagrams());
    assertNotNull(updatedPO.getLanes());
  }
}
