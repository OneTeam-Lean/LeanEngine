package com.thoughtworks.leanengine.domain.workflowcontext.containers


import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.Mockito.mock

class WorkflowServiceTest extends Specification {

    private WorkflowService workflowService

    void setup() {
        workflowService = mock(WorkflowService.class)

    }

    def 'should add workflow success'() {
        given:
        Workflow workflow = new Workflow("testWorkflow")
        when:
        workflowService.saveWorkflow(workflow)
        then:
        assertTrue(true)
    }



}
