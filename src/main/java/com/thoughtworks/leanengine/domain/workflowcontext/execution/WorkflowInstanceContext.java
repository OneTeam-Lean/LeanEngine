package com.thoughtworks.leanengine.domain.workflowcontext.execution;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

public class WorkflowInstanceContext {
  private Map<String, ComponentData> dataMap = newHashMap();
  private List<ComponentData> dataList = newArrayList();

  public WorkflowInstanceContext() {}

  public WorkflowInstanceContext(List<ComponentData> dataList) {
    if (!CollectionUtils.isEmpty(dataList)) {
      this.dataList = dataList;
      this.dataMap =
          dataList.stream().collect(Collectors.toMap(ComponentData::getComponentId, data -> data));
    }
  }

  public void addComponentData(ComponentData componentData) {
    dataMap.put(componentData.getComponentId(), componentData);
    dataList.add(componentData);
  }

  public ComponentData getComponentDataById(String componentId) {
    return dataMap.get(componentId);
  }

  public ComponentData getLastComponentData() {
    if (dataList.isEmpty()) {
      return null;
    }
    return dataList.get(dataList.size() - 1);
  }

  public int getComponentDataSize() {
    return dataList.size();
  }

  public List<ComponentData> getDataListCopy() {
    return newArrayList(dataList);
  }
}
