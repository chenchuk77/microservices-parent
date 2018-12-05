
#### OLD PROCEDURE WITH NON SECURED REGISTRY (PROBLEM WHEN PUSHING FROM MVN)
#1. setup private docker registry on local machine (192.168.99.1)
#docker run -d -p 5000:5000 --restart=always --name registry registry:2
#2. export docker images from dev machine -> this private reg
#chenchuk@dev ~ docker save -o ./fe.tar chenchuk77/frontend
#chenchuk@work ~ docker load -i fe.tar
#3. tag the images
#docker tag chenchuk77/frontend localhost:5000/fe-local-repo
#docker tag chenchuk77/backend localhost:5000/be-local-repo
#4. push to local registry
#docker push localhost:5000/fe-local-repo
#docker push localhost:5000/be-local-repo

################# KUBERNETES ####################################

1. start minikude insecure mode, point it to use the local machine (vbox host machine) as the private reg
# it will launch vm at 192.168.99.100 , the local host machine is 192.168.99.1
# https://medium.com/@alombarte/setting-up-a-local-kubernetes-cluster-with-insecure-registries-f5aaa34851ae
minikube start --insecure-registry="192.168.99.1:5000"

2. add configuration for insecure
minikube addons configure registry-creds
minikube addons enable registry-creds

3. set the current shell
eval $(minikube docker-env)

4. verify
minikube ssh
docker pull 192.168.99.1:5000/fe-local-repo

5. create deployment
kubectl run fe-depl --image 192.168.99.1:5000/fe-local-repo --port 8080

6. expose with service
kubectl expose deployment/fe-depl --type="NodePort" --port 8080

7. check nodeport 
kubectl describe service/fe-depl  ### 32024/TCP

8. check ip
minikube ip  ### 192.168.99.100

9. browse to fe service
http://192.168.99.100:32024/id

10. check depl/svc by label:
kubectl get pods -l run=fe-depl
kubectl get svc -l run=fe-depl



TODO
https://mydeveloperplanet.com/2018/05/30/build-and-deploy-a-spring-boot-app-on-minikube-part-2/












##################### ARBITRARY COMMANDS ##################
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
chenchuk ~/dev/minikube/  
