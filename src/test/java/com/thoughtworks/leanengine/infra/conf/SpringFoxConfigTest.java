package com.thoughtworks.leanengine.infra.conf;

import static io.restassured.RestAssured.when;

import com.thoughtworks.leanengine.ApiTestBase;
import org.junit.jupiter.api.Test;

class SpringFoxConfigTest extends ApiTestBase {

  @Test
  public void shouldEnableSwagger() {
    when().get("/swagger-ui.html").then().statusCode(200);
  }
}
