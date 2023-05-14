This application provides all Customers Information

Pre Requisites : Java 19 and Gradle 7.6 and Postgres

Install Postgres:
docker run --name postgresql -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456789 -p 5432:5432 -d postgres

Used topics:
1.Http all Requests
2.Form Validations
3.PathVariable Validations
4.H2 db by setting up all required info in application.yml variables
5.Used Global Exception handler
6.docker images

------------------------------------------------------------------------------------------------------------------------------------------
Docker build:
step 1: goto below url and run './gradlew clean build'
D:\MyWork\Intellij-projects\spring-boot-order-service
step 2: run the command 'docker build -f ./docker-images/Dockerfile -t spring-boot-order-service:latest .'
step 3: execute the command 'kubectl apply -f ./kubernetes/deployment.yaml'
step 4: execute the command 'kubectl apply -f ./kubernetes/service.yaml'

some of docker commands:
-> docker -–version
-> docker images
-> docker run [image id]
-> docker ps
-> docker stop [container id]
-> docker kill [container id]
-> docker rm [container id]
-> docker login
-> docker push
-> docker build -f [folder path where we have docker file] -t [name for repository:tag] 


------------------------------------------------------------------------------------------------------------------------------------------
All Services Request URLS & Body

Post:
URL:- http://localhost:1003/order-service/order/placeOrder
Body:-
{
"customerID": "12345678",
"productId": [
1,
2,
3
]
}


------------------------------------------------------------------------------------------------------------------------------------------