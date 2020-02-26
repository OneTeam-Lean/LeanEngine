FROM openjdk:8-jdk-alpine

MAINTAINER "Huang Quanbin" <flying.binh@gmail.com>

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]