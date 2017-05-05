FROM openjdk:8-jre

MAINTAINER Piotr Konczak "konczak.piotrek@gmail.com"

ADD target/zipkin-0.0.1.jar zipkin.jar

ENTRYPOINT ["java", "-jar", "/zipkin.jar"]

EXPOSE 9411