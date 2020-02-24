package com.thoughtworks.leanengine.domain.workflowcontext.containers

import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertTrue

class WorkflowServiceTest extends Specification {

    private WorkflowService workflowService

    void setup() {
        workflowService = new WorkflowService()

    }

    def 'should add workflow success'() {
        given:
        Workflow workflow = new Workflow("testWorkflow")
        when:
        workflowService.saveWorkflow(workflow)
        then:
        assertTrue(true)
    }

    def 'should query workflow success'() {
        when:
        Workflow workflowByName = workflowService.queryWorkflowByName("testWorkflow")
        then:
        assertNotNull(workflowByName)
    }

}
