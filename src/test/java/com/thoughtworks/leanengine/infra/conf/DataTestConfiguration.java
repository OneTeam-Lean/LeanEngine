package com.thoughtworks.leanengine.infra.conf;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DataTestConfiguration {

  @Bean
  public DatabaseConfigBean dbUnitDatabaseConfig() {
    DatabaseConfigBean configBean = new DatabaseConfigBean();
    configBean.setEscapePattern("`?`");
    configBean.setDatatypeFactory(new MySqlDataTypeFactory());
    return configBean;
  }

  @Bean(name = "dbUnitDatabaseConnection")
  public DatabaseDataSourceConnection dbUnitDatabaseConnection(
      DataSource dataSource, DatabaseConfigBean configBean) {
    DatabaseDataSourceConnectionFactoryBean factoryBean =
        new DatabaseDataSourceConnectionFactoryBean(dataSource);
    factoryBean.setDatabaseConfig(configBean);
    try {
      return factoryBean.getObject();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}
