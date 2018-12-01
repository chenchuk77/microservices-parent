frontend on port 8080
backend  on port 8085

to build the modules :
as root ( or user that can 'docker ps'):

root/frontend $ mvn clean package dockerfile:build
root/frontend $ docker run -p 8080:8080 -t chenchuk77/frontend

root/backend $ mvn clean package dockerfile:build
root/backend $ docker run -p 8085:8085 -t chenchuk77/backend

===================================================
development :
1. stop service
2. clean, compile, and build docker image
3. run new version of the service

frontend:
docker stop $(docker ps | grep 8080|awk '{print $1}') && \
mvn clean package dockerfile:build && \
docker run -p 8080:8080 -t chenchuk77/frontend

backend:
docker stop $(docker ps | grep 8085|awk '{print $1}') && \
mvn clean package dockerfile:build && \
docker run -p 8085:8085 -t chenchuk77/backend
