#!/bin/bash

docker stop order product1 product2 user zipkin eureka
docker stop user-mongo product-elasticsearch order-postgres
docker rm order product1 product2 user zipkin eureka
docker rmi konczak-microservices/order:0.0.1 konczak-microservices/product:0.0.1 konczak-microservices/user:0.0.1 konczak-microservices/zipkin:0.0.1 konczak-microservices/eureka:0.0.1
docker rm user-mongo product-elasticsearch order-postgres