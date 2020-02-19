package com.thoughtworks.leanengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.thoughtworks.leanengine.infra.conf.DataTestConfiguration;
import com.thoughtworks.leanengine.infra.conf.dbunit.YamlDataSetLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(DataTestConfiguration.class)
@DbUnitConfiguration(
    databaseConnection = "dbUnitDatabaseConnection",
    dataSetLoader = YamlDataSetLoader.class)
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DirtiesContextTestExecutionListener.class,
  TransactionDbUnitTestExecutionListener.class,
  MockitoTestExecutionListener.class
})
@ActiveProfiles("test")
public class ApiTestBase {
  @LocalServerPort private int port;

  @Autowired private ObjectMapper objectMapper;

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
}
