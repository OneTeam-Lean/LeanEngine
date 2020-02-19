package com.thoughtworks.leanengine.adapter;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.thoughtworks.leanengine.ApiTestBase;
import org.junit.jupiter.api.Test;

@DatabaseSetup("/data/person.yml")
@DatabaseTearDown("/data/person.yml")
class PersonControllerTest extends ApiTestBase {

  @Test
  void return_3_person_data_when_request_persons_api() {
    when()
        .get("/persons")
        .then()
        .statusCode(200)
        .body("$.size", is(3))
        .body("[1].name", is("person2"))
        .log();
  }
}
