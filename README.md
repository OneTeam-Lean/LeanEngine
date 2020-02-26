# LeanEngine
## 基本信息
- 【背景】   
针对客户项目中要求的主要基础设施需求进行预研和开发,
相对于市面上其他基于 BPMN2.0 研发的工作流引擎(类似于 activiti7 等)更加灵活,定制化简单.

- 【我们要做什么】   
基于 BPMN2.0 规则开发一款轻量级的工作流引擎

- 【我们的方案是】   
使用目前主流的各种前后端技术和基础设施。我们也非常愿意尝试更加 geek 的工具/框架/思维

## 相关地址
- [Kanban](https://www.teambition.com/project/5e44074c78c0fe0022a10382/tasks/view/all)   
- [https://github.com/OneTeam-Lean/LeanEngine.git](https://github.com/OneTeam-Lean/LeanEngine.git)
- [CI](http://ec2-161-189-97-207.cn-northwest-1.compute.amazonaws.com.cn:9000/), UserName: admin
- [Harbor](http://ec2-161-189-97-207.cn-northwest-1.compute.amazonaws.com.cn:9001/), UserName: admin
- [MongoDB], Host, ec2-161-189-97-207.cn-northwest-1.compute.amazonaws.com.cn, 端口, 9002, UserName: admin
- [Web](http://ec2-161-189-97-207.cn-northwest-1.compute.amazonaws.com.cn:9003/)

## 代码提交规范
- `[Feat]`: new feature for the USER, not a new feature for build script
- `[Fix]`: bug fix for the USER, not a fix to a build script
- `[Refactor]`: refactoring production code e.g. renaming a variable
- `[Chore]`: updating gradle version etc., no production code change
- `[Style]`: CODE style (not front-end style), code formatting, missing semicolon etc.
- `[Docs]`: changes to the documentation, comment etc.

## 技术栈
- Java, jdk8
- MySql, 
- Gradle, 5.6.3
- Spring 
- Spring Boot
- Spring Data JPA
- Flyway
- Docker
- Docker Compose
- Mockito
- GoogleJavaFormat
- Spock2.0

## 项目基本命令
- `./go docker` 启动 local docker
- `./go run` 启动 bootRun
- `./go format` 格式化文件
- `./go clean` gradlew clean
- `./go ci` gradlew clean build
- `./go image` 构建Docker镜像并推送到镜像仓库

## 项目其他说明

本项目使用了代码统一格式的管理，主要是为了减少因为 format code 导致的 commit。
在提交前请使用`./go format` 命令格式化项目代码，同时在 commit 之前也会自动验证格式。
