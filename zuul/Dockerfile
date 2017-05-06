FROM openjdk:8-jre

MAINTAINER Piotr Konczak "konczak.piotrek@gmail.com"

ADD target/zuul-0.0.1.jar zuul.jar

ENTRYPOINT ["java", "-jar", "/zuul.jar"]

EXPOSE 8765