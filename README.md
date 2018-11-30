frontend on port 8080
backend  on port 8085

to build the modules :
as root ( or user that can 'docker ps'):

root/frontend $ mvn clean install dockerfile:build
root/backend $  mvn clean install dockerfile:build