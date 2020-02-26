package com.thoughtworks.leanengine.adapter.restapi;

import com.thoughtworks.leanengine.domain.workflowcontext.enums.Status;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Component;
import com.thoughtworks.leanengine.infra.common.exceptions.WorkflowNotFoundException;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO;
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulator")
@AllArgsConstructor
public class SimulatorController {
  private WorkflowRepository workflowRepository;

  @PutMapping("/{workflowName}/{componentID}/{status}")
  public void modifyComponentStatus(
      @PathVariable String workflowName,
      @PathVariable String componentID,
      @PathVariable Status status) {
    WorkflowPO workflowPO = workflowRepository.findByName(workflowName);
    if (workflowPO == null) {
      throw new WorkflowNotFoundException();
    }
    List<Component> components =
        workflowPO
            .getComponents()
            .stream()
            .map(
                item -> {
                  if (item.isTask()) {
                    Activity task = (Activity) item;
                    if (task.getId().equalsIgnoreCase(componentID)) {
                      task.setStatus(status);
                    }
                  }
                  return item;
                })
            .collect(Collectors.toList());
    workflowPO.setComponents(components);
    workflowRepository.deleteByName(workflowName);
    workflowRepository.save(workflowPO);
  }
}
