# LeanEngine 工作流协议 0.5
本协议基于 BPMN2.0 协议中的规定，并根据需求新增了部分定义。

## 与 BPMN2.0 协议的名词对照

* Lane -> Lane
* Event -> Event
* Task -> Task
* Gateway -> Gateway
* Flow -> Flow

## 协议组件定义

组件分为 4 种类型：

* `Activity` : 用于执行一些动作的组件
* `Flow` : 用于连接所有除 `Workflow` 和 `Lane` 以外的其他组件, 标示他们的执行先后顺序
* `Event` : 用于触发不影响流程本身运行的操作, 类似于发送短信/发送邮件/记录日志等
* `Gateway` : 是一个基于流入 `Flow` 和 `Gateway` 本身类型进行流出 `Flow` 选择的组件



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

## 展示的定义   

展示主要分为 2 个部分来定义
* `Container` : 用于容纳和区分其他两种组件的一种组件，同种 `Container` 之间不能交叉和嵌套
* `Diagram` : 用于表示组件在展示时所处的位置和方向

### Container
#### Workflow 

1. Workflow 是为了将达到某些目标的一系列活动和任务包裹到一起的 `Container` 
2. Workflow 可以容纳 `Lane` , `Flow` , `Event` , `Gateway` 。
3. Workflow 系统顶级 `Container` 。
5. Workflow 中的组件必须包含一个 Start `Event` 和最少一个End `Event` 。
6. Workflow 中的组件流必须由一个 Start `Event` 开始 ，和最少一个End `Event` 结束。
7. Workflow 中的组件之间不能形成环路，即容器中只能包含有向无环图（DAG）。

#### Lane

1. Lane 是为了区分某一段流程的业务含义
2. Lane 是可以容纳 `Flow` , `Event` , `Task` , `Gateway` 组件的 `Container` 。

``` 
暂不使用

#### Job

1. Job 通过有序的容纳一系列的 `Activity` ，完成预期的业务目标
2. Job 只能容纳 `Task` 组件(具体定义与下面 3 中的最终结果相关)
3. Job 在某个 `Task` 执行失败和所有 `Task` 执行成功后的应该有对应的行为发生。（？？是否通过 `Event` 或是单纯是用 Job 的 2 个属性，需要讨论）

```

### Diagram
#### Shape

1. Shape包含一对 X,Y 坐标,用于表示该组件左上角所在的位置
2. Shape包含一对 Width,Height 大小对,用于描述组件的长和宽
3. Shape包含对应组件的 id

#### Edge

1. Edge 包含 2 个 Anchor.Anchor 由 First/Second/Third/Fourth 4 个属性组成.这 4 个属性分别表示的是这个位置是处于其他组件的上左下右四条边的中点位置.
2. Edge 包含 来源组件和目标组件的 id

## DSL 

``` json
{
  "componentType": "WORKFLOW",
  "components": [
    {
      "componentType": "START_EVENT",
      "id": "startEventId",
      "name": "startEvent",
      "task": false
    },
    {
      "componentType": "SEQUENCE_FLOW",
      "flowId": "sequenceFlowId_1",
      "fromComponentId": "startEventId",
      "task": false,
      "toComponentId": "autoTaskId"
    },
    {
      "componentType": "AUTO_TASK",
      "endTime": "2020-02-26T14:28:29.315",
      "id": "autoTaskId",
      "name": "taskName",
      "startTime": "2020-02-26T14:28:29.315",
      "status": "PENDING",
      "task": true
    },
    {
      "componentType": "SEQUENCE_FLOW",
      "flowId": "sequenceFlowId_2",
      "fromComponentId": "autoTaskId",
      "task": false,
      "toComponentId": "manualTaskId"
    },
    {
      "componentType": "MANUAL_TASK",
      "endTime": "2020-02-26T14:28:29.315",
      "id": "manualTaskId",
      "name": "manualTaskName",
      "startTime": "2020-02-26T14:28:29.315",
      "status": "PENDING",
      "task": true
    },
    {
      "componentType": "SEQUENCE_FLOW",
      "flowId": "sequenceFlowId_3",
      "fromComponentId": "manualTaskId",
      "task": false,
      "toComponentId": "endEventId"
    },
    {
      "componentType": "END_EVENT",
      "id": "endEventId",
      "name": "endEvent",
      "task": false
    }
  ],
  "diagrams": [
    {
      "componentId": "startEventId",
      "diagramType": "SHAPE",
      "position": {
        "position_x": 30,
        "position_y": 40
      },
      "size": {
        "height": 10,
        "width": 5
      }
    },
    {
      "diagramType": "EDGE",
      "flowId": "sequenceFlowId_1",
      "sourceAnchor": 1,
      "targetAnchor": 2
    },
    {
      "componentId": "autoTaskId",
      "diagramType": "SHAPE",
      "position": {
        "position_x": 30,
        "position_y": 40
      },
      "size": {
        "height": 20,
        "width": 10
      }
    },
    {
      "diagramType": "EDGE",
      "flowId": "sequenceFlowId_2",
      "sourceAnchor": 1,
      "targetAnchor": 3
    },
    {
      "componentId": "autoTaskId",
      "diagramType": "SHAPE",
      "position": {
        "position_x": 30,
        "position_y": 40
      },
      "size": {
        "height": 20,
        "width": 10
      }
    },
    {
      "diagramType": "EDGE",
      "flowId": "sequenceFlowId_3",
      "sourceAnchor": 4,
      "targetAnchor": 1
    },
    {
      "componentId": "manualTaskId",
      "diagramType": "SHAPE",
      "position": {
        "position_x": 30,
        "position_y": 40
      },
      "size": {
        "height": 20,
        "width": 10
      }
    },
    {
      "componentId": "endEventId",
      "diagramType": "SHAPE",
      "position": {
        "position_x": 30,
        "position_y": 40
      },
      "size": {
        "height": 10,
        "width": 5
      }
    }
  ],
  "lanes": [
    {
      "componentIds": [
        "startEventId",
        "sequenceFlowId_1",
        "autoTaskId",
        "sequenceFlowId_2",
        "manualTaskId",
        "sequenceFlowId_3",
        "endEventId"
      ],
      "componentType": "LANE",
      "laneId": "laneId",
      "name": "testLane",
      "task": false
    }
  ],
  "name": "workflowName",
  "workflowId": null
}
```


