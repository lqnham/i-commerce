# I-Commerce
A small start-up named "iCommerce" wants to build a very simple online shopping
application to sell their products. In order to get to the market quickly, they just want
to build an MVP version with a very limited set of functionalities

## Getting Started

These instructions just apply for my project to do some excercise and practice about java with spring.
It's will continues to enhance and was wrapped with a bunch of feature in the furture

## Prerequisites

What things you need to install the software and how to install them

```
1. Maven   
2. JDK 11
3. Intellij
4. Spring boot 2.3.1.RELEASE
5. Spring Cloud Hoxton.SR5
6. H2 Database
7. Lombok
8. Eureka Server and Client
9. Zuul
10. Using postman if that is your favorite tool
```

## Design system.

![System Design](https://github.com/lqnham/i-commerce/blob/master/resources/Untitled%20Diagram.jpg)

## Entity
![Product Entity](https://github.com/lqnham/i-commerce/blob/master/resources/ProducEntity.png)
![Order Entity](https://github.com/lqnham/i-commerce/blob/master/resources/OrderEntity.png)
![Audit Entity](https://github.com/lqnham/i-commerce/blob/master/resources/AuditEntity.png)


## Folder Structure

Explain what these tests test and why

```
root
|---idiscovery
|---|---IdiscoveryApplication
|---igateway
|---|---IgatewayApplication

these service same structure:
|---iproduct
|---iorder
|---iaudit
|---iaccount
|---|---|---configuration : initialize data needed
|---|---|---controllder : provide restful API
|---|---|---dao : communication with DB
|---|---|---entity : entities mapping with table DB
|---|---|---model : store draw data to let system communication
|---|---|---service : provide method service to handle some logics
|---|---|---|---impl : implement the method from service
|--- README : Introduce about the project and guideline
```

### Installing 
A step by step series that tell you how to get a development env running

```
There module defind as the list:
[x] idiscovery : 8000 -> Building eureka server
[x] igateway  :80 -> Do as gateway
[x] iproduct : 8001 -> Main Service to do something with Product: Search, Filter, provide to our customer what we sell...
[ ] iorder : 8002 -> Give the the way to our customer can buy which product they like.
[x] iaudit : 8003 -> Service to store some action from customer to support audit
[ ] iaccount : 8005 -> Which this service as a customer i can login by easy way as click on login with facebook or google, and let customer can see they shopping card.
```

```
To run the application we need to run [idiscovery] -> [igateway] ->  [the services was list above]
```

### How to ...?
###### Watching database
```
1. Go to the port with service was provide and access
```
![Connect DB](https://github.com/lqnham/i-commerce/blob/master/resources/connectDB.png)
1. username: sa
2. password: password
3. JDBC URL: depend on the module which you want to see
e.g &#8594; you want to see DB of **iproduct** module 
jdbc:h2:file:./**[iproduct]**/src/main/resources/db/**[iproduct]**
and change to the db which one you want to see and list at **installing** item.

### Sample run
```
http://localhost/search?search=price>2004&sortBy=name
http://localhost/findAll
```
