# microservices

I used following OS and tools setup (note it isn't mandatory to match it):
Windows 7
Docket Toolbox 17.03.0-ce
VM RAM: 8GB of 16GB
VM processors: 3 of 4

Note: Bash scripts are more helpers then just free to go runners. It is quite possible that calling init all will fail due to significant memory usage of all containers starting in parallel. Personnaly I recommend starting them one by one or in small groups with delays between each other.

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

~~~
docker rm -f CONTAINER_ID
~~~
stops and removes running container

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

GET 192.168.99.100:9200/_cat/indices?v
	to lookup for list of indexes

~~~
docker pull elasticsearch:2.4.5
~~~

~~~
docker run -d -p 9200:9200 -p 9300:9300 --hostname=elasticsearch --network=microservices --name elasticsearch elasticsearch:2.4.5
~~~

definitely JAVA_OPTS are required - alt extending memory for VM is an option.

## Kibana

~~~
docker pull kibana:4.6.4
~~~

~~~
docker run -d -p 5601:5601 --hostname=kibana --network=microservices --name kibana kibana:4.6.4
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

## Logstash

~~~
docker pull logstash:2.4.1
~~~

~~~
docker run -d -p 5000:5000 --hostname=logstash --network=microservices --name logstash logstash:2.4.1 -e 'input { tcp { port => 5000 codec => "json" } } output { elasticsearch { hosts => ["elasticsearch"] index => "logstash-%{service}"} }'
~~~

a tip for Logback usage, doing:

~~~
<configuration debug="true">
~~~

will start logging what Logback does.