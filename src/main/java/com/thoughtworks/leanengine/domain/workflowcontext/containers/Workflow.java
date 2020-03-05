package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.leanengine.domain.workflowcontext.diagrams.Diagram;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

@Getter
@NoArgsConstructor
public class Workflow extends Container {
  private List<Lane> lanes;
  private List<Component> components;
  private List<Diagram> diagrams;
  @JsonIgnore private Map<String, Component> componentMap;

  public Workflow(
      String name,
      String workflowId,
      List<Lane> lanes,
      List<Component> components,
      List<Diagram> diagrams) {
    super(workflowId, name);
    this.lanes = lanes;
    this.components = components;
    this.diagrams = diagrams;
    if (!CollectionUtils.isEmpty(components)) {
      this.componentMap =
          components.stream().collect(Collectors.toMap(Component::getId, component -> component));
    }
  }
}
