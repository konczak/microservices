#!/bin/bash

#warning this script assumes that all required external images are loaded to local repository - if not execute first script pullDockerImages.sh
#ElasticSearch and Kibana 5.2.1 are ommited in start as well

# Eureka
cd ./eureka/
mvn clean package
docker build -t "konczak-microservices/eureka:0.0.1" .
docker run -d -p 1234:1234 --hostname=eureka --network=microservices --name eureka konczak-microservices/eureka:0.0.1

# Zipkin
cd ../zipkin/
mvn clean package
docker build -t "konczak-microservices/zipkin:0.0.1" .
docker run -d -p 9411:9411 --hostname=zipkin --network=microservices --name zipkin konczak-microservices/zipkin:0.0.1

# User
cd ../user/
docker run -d -p 27017:27017 --hostname=user-mongo --network=microservices --name user-mongo mongo:3.4.4
mvn clean package
docker build -t "konczak-microservices/user:0.0.1" .
docker run -d -p 30000:8080 --hostname=user --network=microservices --name user konczak-microservices/user:0.0.1

# Product
cd ../product/
docker run -d -p 9002:9200 -p 9003:9300 --hostname product-elasticsearch --network=microservices --name product-elasticsearch elasticsearch:2.4.5
mvn clean package
docker build -t "konczak-microservices/product:0.0.1" .
docker run -d -p 30001:8080 --hostname=product1 --network=microservices --name product1 konczak-microservices/product:0.0.1
docker run -d -p 30002:8080 --hostname=product2 --network=microservices --name product2 konczak-microservices/product:0.0.1

# Order
cd ../order/
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres@123 -e POSTGRES_USER=postgres -e POSTGRES_DB=order --hostname=order-postgres --network=microservices --name order-postgres postgres:9.6.2
mvn clean package
docker build -t "konczak-microservices/order:0.0.1" .
docker run -d -p 30003:8080 --hostname=order --network=microservices --name order konczak-microservices/order:0.0.1