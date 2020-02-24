package com.thoughtworks.leanengine.adapter;

import static io.restassured.RestAssured.when;

import com.thoughtworks.leanengine.ApiTestBase;
import org.junit.jupiter.api.Test;

public class WorkflowControllerTest extends ApiTestBase {

  @Test
  void return_workflow_json_when_GET_workflow_api() {
    when().get("/workflow/test").then().statusCode(200).log().body();
  }
}
