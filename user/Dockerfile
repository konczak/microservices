FROM openjdk:8-jre

MAINTAINER Piotr Konczak "konczak.piotrek@gmail.com"

ADD target/user-0.0.1.jar user.jar

ENTRYPOINT ["java", "-jar", "/user.jar"]

EXPOSE 8080