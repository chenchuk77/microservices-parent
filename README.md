# Microservices parent

This is a playground for k8s tests. 
its a 2 module maven project, that creates a microservices in docker containers using spotify/dockerfile-maven-plugin.
The containers can talk to each other, this will help testing Scale-Out and High-Availability
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
- Java > jdk 8
- Maven > 3.0
- User with sudo privileges
- Docker daemon running 
```
$ javac -version
javac 1.8.0_191
$ mvn --version
Apache Maven 3.3.9
$ whoami
chenchuk
$ docker --version
Docker version 18.09.0, build 4d60db4
```

### development cycle
the frontend listens on port 8080, backend on port 8085.
To build and run, use the following procedure :

1. stop service
2. clean, compile, and build docker image
3. run new version of the service

#### Local development (running local containers)

- Backend:
```
docker stop $(docker ps | grep 8085|awk '{print $1}') ; \
mvn clean package dockerfile:build install && \
docker run -p 8085:8085 -t chenchuk77/backend
```
- Frontend: 
- set backend ip when launch
```
docker stop $(docker ps | grep 8080|awk '{print $1}') ; \
mvn clean package dockerfile:build install && \
docker run -p 8080:8080 -e BACKEND_IP=192.168.2.57-t chenchuk77/frontend
```
#### Kubernetes ( push to private registry, no docker run )
```
mvn clean package dockerfile:build install dockerfile:push
```

## Deployment
Add additional notes about how to deploy this on a live system

## Built With
* [Docker](http://https://www.docker.com/) - Containerized framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [SprintBoot](http://spring.io/projects/spring-boot) - Self contained application environment

## References
* Please read [docker-maven-plugin](https://github.com/spotify/docker-maven-plugin) for details on plugin usage.
* Deploy spring-app on Minikube article from[My Developer Planet](https://mydeveloperplanet.com/2018/05/30/build-and-deploy-a-spring-boot-app-on-minikube-part-2/)

## Authors
 **Chen Alkabets**

## License
This project is free for use



















