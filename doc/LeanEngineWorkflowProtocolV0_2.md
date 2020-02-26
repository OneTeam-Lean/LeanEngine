# LeanEngine 工作流协议 0.4
本协议基于 BPMN2.0 协议中的规定，并根据需求新增了部分定义。

## 与 BPMN2.0 协议的名词对照

* Null -> Workflow
* Lane -> Lane
* Event -> Event
* Task -> Task
* Gateway -> Gateway
* Flow -> Flow

## 协议组件定义

组件分为 3 种类型：

* `Container` : 用于容纳和区分其他两种组件的一种组件，同种 `Container` 之间不能交叉和嵌套
* `Activity` : 用于执行一些动作的组件
* `Flow` : 用于连接所有除 `Workflow` 和 `Lane` 以外的其他组件, 标示他们的执行先后顺序
* `Event` : 用于触发不影响流程本身运行的操作, 类似于发送短信/发送邮件/记录日志等
* `Gateway` : 是一个基于流入 `Flow` 和 `Gateway` 本身类型进行流出 `Flow` 选择的组件

### Workflow ( `Container` )

1. Workflow 是为了将达到某些目标的一系列活动和任务包裹到一起的 `Container` 
2. Workflow 可以容纳 `Lane` , `Flow` , `Event` , `Gateway` 。
3. Workflow 系统顶级 `Container` 。
5. Workflow 中的组件必须包含一个 Start `Event` 和最少一个End `Event` 。
6. Workflow 中的组件流必须由一个 Start `Event` 开始 ，和最少一个End `Event` 结束。
7. Workflow 中的组件之间不能形成环路，即容器中只能包含有向无环图（DAG）。

### Lane ( `Container` )

1. Lane 是为了区分某一段流程的业务含义
2. Lane 是可以容纳 `Flow` , `Event` , `Task` , `Gateway` 组件的 `Container` 。

``` 
暂不使用

### Job( `Container` )

1. Job 通过有序的容纳一系列的 `Activity` ，完成预期的业务目标
2. Job 只能容纳 `Task` 组件(具体定义与下面 3 中的最终结果相关)
3. Job 在某个 `Task` 执行失败和所有 `Task` 执行成功后的应该有对应的行为发生。（？？是否通过 `Event` 或是单纯是用 Job 的 2 个属性，需要讨论）

```

### Flow ( `Flow` )

1. Flow 是一个单方向流向指示组件。
2. Flow 可以指示 2 个 `Gateway` , `Event` 的流向。
3. Flow 可以有描述信息来说明流程的业务含义。

### Gateway ( `Gateway` )

 1. Gateway 通常是单个 `Flow` 流入, 多个 `Flow` 流出
 2. Gateway 有多种分类:

    - 判断 Gateway:
      1. 通过内置的判断机制进行选择接下来流向方向
    - 并行 Gateway:
      1. 同时触发接下来的多个流出的 `Flow` 
    - 包含 Gateway:
      1. 成对出现,对被包含在开始和结束 Gateway 中的内容进行统一处理(可能只会有类似于迭代的概念?)  

### Event ( `Event` ) 

1. Event 包含三种类型:

   - Start 开始
   - End 结束
   - Intermediate 中间 

2. `Workflow` 中的流程必须以 Start `Event` 开始, 以 End `Event` 结束

3. Intermediate `Event` 主要用于在流程中触发一些通知或记录动作, 对流程本身的执行不产生任何影响

#### Event 基本属性

### Task ( `Activity` )(需要确定业务后再扩展)

1. Task 是用于执行具体业务的组件
2. Task 可以根据不同业务分为很多基础种类

   1. AutoTask
   2. ManualTask

## DSL 

``` json
{
    "componentType": "WORKFLOW",
    "name": "apiTestSave",
    "workflowId": "5e55e3868fea0e1bd82e3096",
    "lanes": [
        {
            "componentType": "LANE",
            "name": "testLane",
            "laneId": "laneId",
            "componentIds": [
                "startEventId",
                "sequenceFlowId_1",
                "autoTaskId",
                "sequenceFlowId_2",
                "manualTaskId",
                "sequenceFlowId_3",
                "endEventId"
            ]
        }
    ],
    "components": [
        {
            "componentType": "START_EVENT",
            "id": "startEventId",
            "name": "startEvent"
        },
        {
            "componentType": "SEQUENCE_FLOW",
            "flowId": "sequenceFlowId_1",
            "fromComponentId": "startEventId",
            "toComponentId": "autoTaskId"
        },
        {
            "componentType": "AUTO_TASK"
        },
        {
            "componentType": "SEQUENCE_FLOW",
            "flowId": "sequenceFlowId_2",
            "fromComponentId": "autoTaskId",
            "toComponentId": "manualTaskId"
        },
        {
            "componentType": "MANUAL_TASK"
        },
        {
            "componentType": "SEQUENCE_FLOW",
            "flowId": "sequenceFlowId_3",
            "fromComponentId": "manualTaskId",
            "toComponentId": "endEventId"
        },
        {
            "componentType": "END_EVENT",
            "id": "endEventId",
            "name": "endEvent"
        }
    ],
    "diagrams": [
        {
            "diagramType": "SHAPE",
            "componentId": "startEventId",
            "size": {
                "width": 5,
                "height": 10
            },
            "position": {
                "position_x": 30,
                "position_y": 40
            }
        },
        {
            "diagramType": "EDGE",
            "flowId": "sequenceFlowId_1",
            "startPosition": {
                "position_x": 100,
                "position_y": 200
            },
            "endPosition": {
                "position_x": 300,
                "position_y": 400
            }
        },
        {
            "diagramType": "SHAPE",
            "componentId": "autoTaskId",
            "size": {
                "width": 10,
                "height": 20
            },
            "position": {
                "position_x": 30,
                "position_y": 40
            }
        },
        {
            "diagramType": "EDGE",
            "flowId": "sequenceFlowId_2",
            "startPosition": {
                "position_x": 100,
                "position_y": 200
            },
            "endPosition": {
                "position_x": 300,
                "position_y": 400
            }
        },
        {
            "diagramType": "SHAPE",
            "componentId": "autoTaskId",
            "size": {
                "width": 10,
                "height": 20
            },
            "position": {
                "position_x": 30,
                "position_y": 40
            }
        },
        {
            "diagramType": "EDGE",
            "flowId": "sequenceFlowId_3",
            "startPosition": {
                "position_x": 100,
                "position_y": 200
            },
            "endPosition": {
                "position_x": 300,
                "position_y": 400
            }
        },
        {
            "diagramType": "SHAPE",
            "componentId": "manualTaskId",
            "size": {
                "width": 10,
                "height": 20
            },
            "position": {
                "position_x": 30,
                "position_y": 40
            }
        },
        {
            "diagramType": "SHAPE",
            "componentId": "endEventId",
            "size": {
                "width": 5,
                "height": 10
            },
            "position": {
                "position_x": 30,
                "position_y": 40
            }
        }
    ]
}
```

## 类图

``` mermaid
classDiagram

    Component <|-- Container
    Component <|-- Gateway
    Component <|-- Activity
    Component <|-- Flow
    Component <|-- Event
    Component --o  Version

    Container <|.. Workflow
    Container <|.. Lane

    Workflow  --o  WorkflowModule

    Event     <|.. StartEvent
    Event     <|.. EndEvent
    Event     <|.. IntermediateEvent

    Gateway   <|.. InclusiveGateway
    Gateway   <|.. ExclusiveGateway
    Gateway   <|.. ParallelGateway
   
    Flow      <|.. SequenceFlow
    Activity  <|.. Task

    FlowLogger

    class Component{
        <<interface>>
        Comment;
        Positions;
        Version;
        StartDate;
        EndDate;
        Status;
    }

    class Version{
        VersionCode;
        BeforeVersion;
        ToVersion;
        CreateDate;
    }
    class Container{
        <<interface>>
        Components;
    }
    class Gateway{
        <<interface>>
        NextFlows;
        JudgementFunction;
    }
    class Activity{
        <<interface>>
        ExecuteFunction;
    }
    class Flow{
        <<interface>>
        FromComponent;
        ToComponect;
    }
    class Event{
        <<interface>>
        Consumer;
    }

    class Workflow{
        <<class>>
        GlobalData;
    }
    class Lane{
        <<class>>
    }
    class WorkflowModule{
        <<class>>
        ModuleName;
        Version;
        Workflow;
    }
    class ExclusiveGateway{
        <<class>>
    }
    class InclusiveGateway{
        <<class>>
    }
    class ParallelGateway{
        <<class>>
    }
    
    class Task{
        <<class>>
    }

    class StartEvent{
        <<class>>
    }
    class EndEvent{
        <<class>>
    }
    class IntermediateEvent{
        <<class>>
    }

    class SequenceFlow{
        <<class>>
    }

    class FlowLogger{
        <<class>>
        Logger;
        MessageMaker;
    }

```

