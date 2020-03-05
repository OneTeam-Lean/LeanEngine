package com.thoughtworks.leanengine.domain.workflowcontext.data;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ComponentData {
  private String componentId;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Boolean runningResult;
  private Map<String, Object> data;
}
