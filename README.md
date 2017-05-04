# microservices

~~docker pull netflixoss/eureka:1.1.142~~

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
