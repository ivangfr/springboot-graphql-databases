# springboot-graphql-mysql

## Goal

The goal of this project is to implement an API that uses [GraphQL](https://graphql.org) and explore
what GrapghQL has to offer. `author-api` is a spring-boot application that has GraphQL endpoints together with the
traditional REST endpoints. `author-api` uses [MySQL](https://www.mysql.com) as storage. Besides, we will implement
another micro-service, called `author-client`, that will consume `author-api` and display the information in an user
friendly interface implemented using [Thymeleaf](https://www.thymeleaf.org).

## Start Environment

### Docker Compose

- Open one terminal

- In `/springboot-graphql-mysql` root folder run
```
docker-compose up -d
```
>
> To stop and remove containers, networks, images, and volumes
>```
>docker-compose down -v
>```

- Wait a little bit until all containers are Up (healthy). You can check their status running
```
docker-compose ps
```

## Start Services

### eureka-server

- Open a new terminal
- Inside `/springboot-graphql-mysql/eureka-server` folder run
```
mvn spring-boot:run
```
- The link for eureka-server is http://localhost:8761

### author-api

- Open a new terminal
- Inside `/springboot-graphql-mysql/author-api` folder run
```
mvn spring-boot:run
```

#### Rest API

- The link for author-api `Swagger` is: http://localhost:8081/swagger-ui.html

#### GraphQL

- The link for author-api `GraphiQL` is: http://localhost:8081/graphiql

### author-client

- Open a new terminal
- Inside `/springboot-graphql-mysql/author-client` folder run
```
mvn spring-boot:run
```
- The link for author-client website is http://localhost:8082

## How to use GraphiQL

- access http://localhost:8081/graphiql

- create author
```
mutation {
  createAuthor(firstName:"Ivan", lastName:"Franchin") {
    id
  }
}
```

- find all authors
```
{
  findAllAuthors {
    id, firstName, lastName
  }
}
```

### Useful links

- Zipkin can be accessed at http://localhost:9411

## TODO

## References

- https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
- https://www.baeldung.com/spring-graphql