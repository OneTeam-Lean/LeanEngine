package com.thoughtworks.leanengine.infra.repo.po;

import java.io.Serializable;

public interface PersistenceObject<T> extends Serializable {
  T toDomainModel();
}
