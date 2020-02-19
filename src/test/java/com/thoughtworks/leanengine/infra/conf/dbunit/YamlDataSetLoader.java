package com.thoughtworks.leanengine.infra.conf.dbunit;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.IDataSet;
import org.springframework.core.io.Resource;

public class YamlDataSetLoader extends AbstractDataSetLoader {
  @Override
  protected IDataSet createDataSet(final Resource resource) throws Exception {
    return new YamlDataSet(resource.getFile());
  }
}
