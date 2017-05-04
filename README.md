# microservices

## Service discovery with Eureka

~~docker pull netflixoss/eureka:1.1.142~~

~~~
docker logs NAME_OF_CONTAINER
~~~

prints last logs from container

in folder with eureka call

~~~
docker build -t "konczak-microservices/eureka:0.0.1" .
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

to run our Eureka and allow access on port 1234 call:

~~~
docker run -p 1234:8080 --name eureka konczak-microservices/eureka:0.0.1
~~~

## ElasticSearch (for logs and products)

https://www.elastic.co/guide/en/elasticsearch/reference/5.2/docker.html

~~~
docker pull docker.elastic.co/elasticsearch/elasticsearch:5.2.1
~~~

Extend vm_max_map_count in Docket Toolbox

~~~
docker-machine ssh
sudo sysctl -w vm.max_map_count=262144
~~~

Oracle VirtualBox
Extend RAM and processors available for Docker VM.

~~~
docker run -d -e ES_JAVA_OPTS="-Xms2g -Xmx2g" -e "http.host=0.0.0.0" -e "transport.host=127.0.0.1" -e "xpack.security.enabled=false" --name elasticsearch -p 9200:9200 -p 9300:9300 docker.elastic.co/elasticsearch/elasticsearch:5.2.1
~~~

definitely JAVA_OPTS are required - alt extending memory for VM is an option.

## Kibana

~~~
docker pull docker.elastic.co/kibana/kibana:5.2.1
~~~

~~~
docker run -d -e "ELASTICSEARCH_URL=http://192.168.99.100:9200" -e "xpack.security.enabled=false" -e "xpack.monitoring.ui.container.elasticsearch.enabled=false" --name kibana -p 5601:5601 docker.elastic.co/kibana/kibana:5.2.1
~~~

to list indices GET 192.168.99.100:9200/_cat/indices?v

