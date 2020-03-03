#!/bin/bash

work_dir=`dirname $0`
cd "$work_dir"
echo " Working Directory: $work_dir "

#docker image parameters
tag=$2
docker_registry="ec2-161-189-97-207.cn-northwest-1.compute.amazonaws.com.cn:9001"
docker_repository="lean-engine"
app_image_name=${docker_registry}/$docker_repository/workflow
app_image_fullname=${app_image_name}:${tag:-unknown}
docker_comtainer_name="lean-api"

case "$1" in
  "docker")
    docker-compose -f config/local/docker-compose.yml down
    docker-compose -f config/local/docker-compose.yml up -d
    ;;
  "run")
    ./gradlew  bootRun -Plocal
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
  "image")
    ./gradlew clean build
    docker build --rm -t ${app_image_fullname} .
    docker login -u admin -p $3 $docker_registry
    docker push ${app_image_fullname}
    exit 0
    ;;
  "deploy")
    docker stop ${docker_comtainer_name}
    docker run --rm --name ${docker_comtainer_name} -d -e "SPRING_PROFILES_ACTIVE=prod" -p 9005:8080 ${app_image_fullname}
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
