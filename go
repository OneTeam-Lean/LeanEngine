#!/bin/bash

work_dir=`dirname $0`
cd "$work_dir"
echo " Working Directory: $work_dir "

repository=
tag=1
app_image=online-reservation-api:${tag}

case "$1" in
  "docker")
    docker-compose -f config/local/docker-compose.yml down
    docker-compose -f config/local/docker-compose.yml up -d
    ;;
  "run")
    ./gradlew -Dspring.profiles.active="local" bootRun
    exit 0
    ;;
  "health")
    curl -s http://localhost:8080/actuator/health
    ;;
  "format")
    ./gradlew goJF
    ;;
  "info")
    curl -s http://localhost:8080/actuator/info
    ;;
  "clean")
    ./gradlew clean
    exit 0
    ;;
  "ci")
    ./gradlew clean build
    exit 0
    ;;
  "migration")
    ./gradlew flywayMigrate -i
    ;;
  *)
    ./gradlew goJF
    ./gradlew clean build
    exit 0
    ;;
esac
