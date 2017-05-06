# microservices

## Docker tips

~~~
docker logs NAME_OF_CONTAINER
~~~

prints last logs from container

~~~
docker build -t "IMAGE_NAME:VERSION" .
~~~

will build Docker image from Dockerfile in location "." which means current.
-t allows specify name and tag

~~~
docker run -d
~~~

-d means detached container

~~~
docker run -p ip:host-port:container-port
~~~

-p maps container port to the host one with specified IP which can be omitted

~~~
docker-machine ip default
~~~

to find out what IP can be used to get into container

~~~
docker ps -a -q --filter=ancestor=mikesplain/telnet | xargs -I {} docker rm {}
~~~
will remove all unused docker containers based on specified image

## Todos

~~~
docker network create --driver bridge microservices
~~~

Extend vm_max_map_count in Docket Toolbox

~~~
docker-machine ssh
sudo sysctl -w vm.max_map_count=262144
~~~

In Oracle VirtualBox
Extend RAM and processors available for Docker VM.

## Service discovery with Eureka

in folder with eureka call

~~~
mvn clean package
~~~

~~~
docker build -t "konczak-microservices/eureka:0.0.1" .
~~~

to run our Eureka and allow access on port 1234 call:

~~~
docker run -d -p 1234:1234 --hostname=eureka --network=microservices --name eureka konczak-microservices/eureka:0.0.1
~~~

## ElasticSearch (for logs and products)

https://www.elastic.co/guide/en/elasticsearch/reference/5.2/docker.html

GET 192.168.99.100:9200/_cat/indices?v
	to lookup for list of indexes

~~~
docker pull docker.elastic.co/elasticsearch/elasticsearch:5.2.1
~~~

~~~
docker run -d -e ES_JAVA_OPTS="-Xms2g -Xmx2g" -e "http.host=0.0.0.0" -e "transport.host=127.0.0.1" -e "xpack.security.enabled=false" --hostname elasticsearch --net=microservices --name elasticsearch -p 9200:9200 -p 9300:9300 docker.elastic.co/elasticsearch/elasticsearch:5.2.1
~~~

definitely JAVA_OPTS are required - alt extending memory for VM is an option.

## Kibana

~~~
docker pull docker.elastic.co/kibana/kibana:5.2.1
~~~

~~~
docker run -d -e "ELASTICSEARCH_URL=http://elasticsearch:9200" -e "xpack.security.enabled=false" -e "xpack.monitoring.ui.container.elasticsearch.enabled=false" --hostname kibana --net=microservices --name kibana -p 5601:5601 docker.elastic.co/kibana/kibana:5.2.1
~~~

to list indices GET 192.168.99.100:9200/_cat/indices?v

## Zipkin
In zipkin folder call

~~~
mvn clean package
~~~

~~~
docker build -t "konczak-microservices/zipkin:0.0.1" .
~~~

~~~
docker run -d -p 9411:9411 --hostname=zipkin --network=microservices --name zipkin konczak-microservices/zipkin:0.0.1
~~~

## Mongo for User
~~~
docker pull mongo:3.4.4
~~~

~~~
docker run -d -p 27017:27017 --hostname=user-mongo --network=microservices --name user-mongo mongo:3.4.4
~~~

## User
In user folder call

~~~
mvn clean package
~~~

~~~
docker build -t "konczak-microservices/user:0.0.1" .
~~~

~~~
docker run -d -p 30000:8080 --hostname=user --network=microservices --name user konczak-microservices/user:0.0.1
~~~

## ELasticSearch for Product
~~~
docker pull elasticsearch:2.4.5
~~~

~~~
docker run -d -p 9002:9200 -p 9003:9300 --hostname product-elasticsearch --network=microservices --name product-elasticsearch elasticsearch:2.4.5
~~~

## Product
Spring boot 1.5 does not support yet ElasticSearch >=5.0.0
In product folder call

~~~
mvn clean package
~~~

~~~
docker build -t "konczak-microservices/product:0.0.1" .
~~~

~~~
docker run -d -p 30001:8080 --hostname=product1 --network=microservices --name product1 konczak-microservices/product:0.0.1
~~~

~~~
docker run -d -p 30002:8080 --hostname=product2 --network=microservices --name product2 konczak-microservices/product:0.0.1
~~~

## Postgres for Order

~~~
docker pull postgres:9.6.2
~~~

~~~
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres@123 -e POSTGRES_USER=postgres -e POSTGRES_DB=order --hostname=order-postgres --network=microservices --name order-postgres postgres:9.6.2
~~~

## Order
In order folder call

~~~
mvn clean package
~~~

~~~
docker build -t "konczak-microservices/order:0.0.1" .
~~~

~~~
docker run -d -p 30003:8080 --hostname=order --network=microservices --name order konczak-microservices/order:0.0.1
~~~

Hystrix dashboard is available under /hystrix to feed it with data specify /hystrix.stream

## Gateway with Zuul
In zuul folder call

~~~
mvn clean package
~~~

~~~
docker build -t "konczak-microservices/zuul:0.0.1" .
~~~

~~~
docker run -d -p 8765:8765 --hostname=zuul --network=microservices --name zuul konczak-microservices/zuul:0.0.1
~~~