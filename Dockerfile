FROM oracle/openjdk:8

MAINTAINER "Huang Quanbin" <flying.binh@gmail.com>

ADD build/libs/LeanEngine-1.0.0-SNAPSHOT.jar api.jar
ENV JAVA_OPTS=""
CMD [ "sh", "-c", "java $JAVA_OPTS -jar api.jar" ]