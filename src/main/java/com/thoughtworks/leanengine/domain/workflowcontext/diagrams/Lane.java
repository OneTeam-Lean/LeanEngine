package com.thoughtworks.leanengine.domain.workflowcontext.diagrams;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lane {
  private String id;
  private String name;
  private List<String> componentIds;

  public Lane(String name, String laneId, List<String> componentIds) {
    this.name = name;
    this.id = laneId;
    this.componentIds = componentIds;
  }
}
