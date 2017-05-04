FROM openjdk:8-jre

MAINTAINER Piotr Konczak "konczak.piotrek@gmail.com"

ADD target/eureka-0.0.1.jar eureka.jar

ENTRYPOINT ["java", "-jar", "/eureka.jar"]

EXPOSE 8080