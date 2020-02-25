package com.thoughtworks.leanengine.infra.util;

import org.dozer.DozerBeanMapper;

public class MapperUtil {

  public static <T> T map(Object object, Class<T> tClass) {
    return new DozerBeanMapper().map(object, tClass);
  }
}
