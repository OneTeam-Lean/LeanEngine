package com.thoughtworks.leanengine.infra.conf.dbunit;

import static org.junit.Assert.assertTrue;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.thoughtworks.leanengine.ApiTestBase;
import org.junit.Ignore;
import org.junit.Test;

@DatabaseSetup("/data/yml_test.yml")
@DatabaseTearDown("/data/yml_test.yml")
public class YamlTest extends ApiTestBase {

  @Test
  @Ignore
  public void shouldParseDateSuccess() {
    assertTrue(Boolean.TRUE);
  }
}
