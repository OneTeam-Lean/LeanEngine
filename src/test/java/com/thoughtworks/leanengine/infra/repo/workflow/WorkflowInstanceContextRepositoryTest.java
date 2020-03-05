package com.thoughtworks.leanengine.infra.repo.workflow;

import static com.google.common.collect.Maps.newHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thoughtworks.leanengine.ApiTestBase;
import com.thoughtworks.leanengine.domain.workflowcontext.data.ComponentData;
import com.thoughtworks.leanengine.domain.workflowcontext.data.WorkflowInstanceContext;
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowInstanceContextPO;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class WorkflowInstanceContextRepositoryTest extends ApiTestBase {
  @Autowired private WorkflowInstanceContextRepository workflowInstanceContextRepository;

  @Test
  void should_be_save_instance_success_when_save_a_instance() {
    WorkflowInstanceContext workflowInstanceContext = initWorkflowInstanceContext();
    WorkflowInstanceContextPO contextPO =
        WorkflowInstanceContextPO.of("workflowId", workflowInstanceContext);
    workflowInstanceContextRepository.save(contextPO);
  }

  @Test
  void should_be_instance_correct_when_query_instance_by_id_workflowId() {
    WorkflowInstanceContext workflowInstanceContext = initWorkflowInstanceContext();
    WorkflowInstanceContextPO contextPO =
        WorkflowInstanceContextPO.of("workflowId", workflowInstanceContext);
    workflowInstanceContextRepository.save(contextPO);

    Optional<WorkflowInstanceContextPO> poOptional =
        workflowInstanceContextRepository.findById("workflowId");
    assertTrue(poOptional.isPresent());
    WorkflowInstanceContext context = poOptional.get().toDomainModel();
    assertEquals(3, context.getComponentDataSize());
    assertNotNull(context.getComponentDataById("test2"));
    ComponentData componentData = context.getComponentDataById("test2");
    assertEquals("test2", componentData.getComponentId());
    assertEquals("1234", componentData.getData().get("testString"));
    assertEquals(1234, componentData.getData().get("testInt"));
    assertTrue(Date.class.isAssignableFrom(componentData.getData().get("testDateTime").getClass()));
  }

  @Test
  void should_be_update_instance_success_when_update_a_instance() {
    WorkflowInstanceContext workflowInstanceContext = initWorkflowInstanceContext();
    WorkflowInstanceContextPO contextPO =
        WorkflowInstanceContextPO.of("workflowId", workflowInstanceContext);
    workflowInstanceContextRepository.save(contextPO);
    Map<String, Object> dataMap = newHashMap();
    dataMap.put("testString", "1234");
    dataMap.put("testInt", 1234);
    ComponentData test4 =
        new ComponentData("test4", LocalDateTime.now(), LocalDateTime.now(), true, dataMap);
    workflowInstanceContext.addComponentData(test4);
    workflowInstanceContextRepository.save(
        WorkflowInstanceContextPO.of("workflowId", workflowInstanceContext));
    Optional<WorkflowInstanceContextPO> optional =
        workflowInstanceContextRepository.findById("workflowId");
    assertTrue(optional.isPresent());
    WorkflowInstanceContext context = optional.get().toDomainModel();
    assertNotNull(context.getComponentDataById("test4"));
    ComponentData data = context.getComponentDataById("test4");
    assertNotNull(data);
    assertEquals("1234", data.getData().get("testString"));
    assertNull(data.getData().get("testDateTime"));
  }

  private WorkflowInstanceContext initWorkflowInstanceContext() {
    WorkflowInstanceContext workflowInstanceContext = new WorkflowInstanceContext();
    Map<String, Object> dataMap = newHashMap();
    dataMap.put("testString", "1234");
    dataMap.put("testInt", 1234);
    dataMap.put("testDateTime", LocalDateTime.now());
    ComponentData test1 =
        new ComponentData("test1", LocalDateTime.now(), LocalDateTime.now(), true, dataMap);
    ComponentData test2 =
        new ComponentData("test2", LocalDateTime.now(), LocalDateTime.now(), true, dataMap);
    ComponentData test3 =
        new ComponentData("test3", LocalDateTime.now(), LocalDateTime.now(), true, dataMap);
    workflowInstanceContext.addComponentData(test1);
    workflowInstanceContext.addComponentData(test2);
    workflowInstanceContext.addComponentData(test3);
    return workflowInstanceContext;
  }
}
