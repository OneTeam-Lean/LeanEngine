package com.thoughtworks.leanengine.domain.workflowcontext.data;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class WorkflowInstanceContext {
  private Map<String, ComponentData> dataMap = newHashMap();
  private List<ComponentData> dataList = newArrayList();

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
}
