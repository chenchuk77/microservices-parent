#### DOCKER PRIVATE REGISTRY on 192.168.99.1

1. set self sign cert
```
~/ mkdir -p certs
~/ openssl req -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key -x509 -days 365 -out certs/domain.crt
```
2. run registry with ssl support
```
~/ docker run -d --restart=always --name registry -v `pwd`/certs:/certs \
    -e REGISTRY_HTTP_ADDR=0.0.0.0:443 \
    -e REGISTRY_HTTP_TLS_CERTIFICATE=/certs/domain.crt \
    -e REGISTRY_HTTP_TLS_KEY=/certs/domain.key \
    -p 443:443 registry:2
```
3. trust this self signed cert by copy certs/domain.crt to /etc/docker/certs.d/myregistrydomain.com/ca.crt (maven cant push if not trusted) on all docker hosts:
- dev machine
- minikube ( after login to minikube via 'minikube ssh')
- host machine (maybe not necessary

#### KUBERNETES

1. start minikude 
```
~/ minikube start 
```

2. add configuration for local registry: 
```
~/ minikube addons configure registry-creds 
                      ### choose: n,n,y,myregistrydomain.com
~/ minikube addons enable registry-creds
```

3. set the current shell
```
~/ eval $(minikube docker-env)
```

4. verify
```
~/ minikube ssh
$ docker pull myregistrydomain.com/chenchuk77/backend:3.3-SNAPSHOT
$ docker pull myregistrydomain.com/chenchuk77/frontend:2.7-SNAPSHOT     

```
5. create deployment
```
~/ kubectl run fe-depl --image myregistrydomain.com/chenchuk77/frontend:2.7-SNAPSHOT --port 8080
```

6. expose with service
```
~/ kubectl expose deployment/fe-depl --type="NodePort" --port 8080
```

7. verify 
```
~/ minikube ip       # returns 192.168.99.100
~/ kubectl get svc   # returns 32458
~/ curl http://192.168.99.100:32458/id

```

#### More minikube/kubectl commands:

```
kubectl run hello-minikube --image=k8s.gcr.io/echoserver:1.10 --port=8080
kubectl expose deployment hello-minikube --type=NodePort
kubectl get pod
kubectl get svc
minikube ip
minikube dashboard       # web console
minikube service list    # service exposed via node-port
minikube ssh             # ssh to the minikube vm (virtualbox)

kubectl delete deployment fe-depl
kubectl get deployment
kubectl get pods
kubectl describe pods
kubectl logs fe-depl-949bf9fd-bw2dw
kubectl exec fe-depl-949bf9fd-bw2dw env # run shell command
kubectl get svc # service used to expose pods outside the cluster
kubectl describe service/fe-depl
```