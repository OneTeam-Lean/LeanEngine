package com.thoughtworks.leanengine.domain.workflowcontext.containers;

import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Container;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lane extends Container {
  private List<String> ComponentIds;

  public Lane(String name, String laneId, List<String> componentIds) {
    super(name);
    setId(laneId);
    ComponentIds = componentIds;
  }
}
