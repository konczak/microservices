FROM openjdk:8-jre

MAINTAINER Piotr Konczak "konczak.piotrek@gmail.com"

ADD target/product-0.0.1.jar product.jar

ENTRYPOINT ["java", "-jar", "/product.jar"]

EXPOSE 8080