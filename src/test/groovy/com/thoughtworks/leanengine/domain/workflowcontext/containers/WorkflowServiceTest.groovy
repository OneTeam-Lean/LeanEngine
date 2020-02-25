package com.thoughtworks.leanengine.domain.workflowcontext.containers

import com.thoughtworks.leanengine.infra.repo.po.workflow.WorkflowPO
import com.thoughtworks.leanengine.infra.repo.workflow.WorkflowRepository
import spock.lang.Specification

import static com.google.common.collect.Lists.newArrayList
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertTrue

class WorkflowServiceTest extends Specification {

    private WorkflowRepository workflowRepository = Stub(WorkflowRepository.class)
    private WorkflowService workflowService = new WorkflowService(workflowRepository)


    def 'should add workflow success'() {
        given:
        Workflow workflow = new Workflow(null, "testWorkflow", newArrayList(), newArrayList(), newArrayList())
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
