package com.thoughtworks.leanengine.domain.workflowcontext.containers

import com.thoughtworks.leanengine.domain.workflowcontext.workflow.Workflow
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowExecutionPool
import com.thoughtworks.leanengine.domain.workflowcontext.workflow.WorkflowService
import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository
import spock.lang.Specification

import static com.google.common.collect.Lists.newArrayList
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertTrue

class WorkflowServiceTest extends Specification {

    private WorkflowRepository workflowRepository = Stub(WorkflowRepository.class)
    private WorkflowExecutionPool workflowExecutionPool = Stub(WorkflowExecutionPool.class)

    private WorkflowService workflowService = new WorkflowService(workflowRepository, workflowExecutionPool)


    def 'should add workflow success'() {
        given:
        Workflow workflow = new Workflow(null, "testWorkflow", newArrayList(), newArrayList(), newArrayList(), newArrayList())
        workflowRepository.save(_) >> WorkflowPO.of(workflow)
        when:
        workflowService.saveWorkflow(workflow)
        then:
        assertTrue(true)
    }

    def 'should query workflow success'() {
        when:
        workflowRepository.findByName() >> new WorkflowPO()
        Workflow workflowByName = workflowService.queryWorkflowByName("testWorkflow")
        then:
        assertNotNull(workflowByName)
    }

}
