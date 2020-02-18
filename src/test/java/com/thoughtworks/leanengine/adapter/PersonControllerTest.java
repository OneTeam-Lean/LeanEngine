package com.thoughtworks.leanengine.adapter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DatabaseSetup("classpath:data/person.xml")
@DatabaseTearDown
public class PersonControllerTest {
  @LocalServerPort int port;

  @Test
  void return_3_person_data_when_request_persons_api() {
    given().port(port).when().get("/persons").then().statusCode(200).body("$.size", is(3));
  }
}
