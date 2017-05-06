FROM openjdk:8-jre

MAINTAINER Piotr Konczak "konczak.piotrek@gmail.com"

ADD target/order-0.0.1.jar order.jar

ENTRYPOINT ["java", "-jar", "/order.jar"]

EXPOSE 8080