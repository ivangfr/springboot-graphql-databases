# springboot-graphql-databases

## Goal

The goal of this project is to implement an API that uses [GraphQL](https://graphql.org) and explore
what GrapghQL has to offer. `author-api` is a spring-boot application that has GraphQL endpoints together with the
traditional REST endpoints. `author-api` uses [MySQL](https://www.mysql.com) as storage. Besides, we will implement
another micro-service, called `author-client`, that will consume `author-api` and display the information in an user
friendly interface implemented using [Thymeleaf](https://www.thymeleaf.org).

## Start Environment

### Docker Compose

- Open one terminal

- In `/springboot-graphql-databases` root folder run
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
- Inside `/springboot-graphql-databases/eureka-server` folder run
```
mvn spring-boot:run
```
- The link for eureka-server is http://localhost:8761

### author-api

- Open a new terminal
- Inside `/springboot-graphql-databases/author-api` folder run
```
mvn spring-boot:run
```

#### Rest API

- The link for author-api `Swagger` is: http://localhost:8081/swagger-ui.html

#### GraphQL

- The link for author-api `GraphiQL` is: http://localhost:8081/graphiql

### author-client

- Open a new terminal
- Inside `/springboot-graphql-databases/author-client` folder run
```
mvn spring-boot:run
```
- The link for author-client website is http://localhost:8082

## How to use GraphiQL

- access http://localhost:8081/graphiql

- create author and return the id
```
mutation {
  createAuthor(createAuthorInput: {firstName: "Ivan", lastName: "Franchin"}) {
    id
  }
}
```

- create book and return the id of the book, first and last name of the author
```
mutation {
  createBook(createBookInput: {authorId: 1, title: "C++", year: 2018, numPages: 512}) {
    id
    author {
      firstName
      lastName
    }
  }
}
```

- get author by id and return all information about his/her books
```
{
  getAuthor(id: 1) {
    id
    firstName
    lastName
    books {
      id
      title
      year
      numPages
    }
  }
}
```

- update book title and return its id and new title
```
mutation {
  updateBook(id: 2, updateBookInput: {title: "Java10"}) {
    id
    title
  }
}
```

- delete author and return his/her id
```
mutation {
  deleteAuthor(id: 1) {
    id
  }
}
```

### Useful links

- Zipkin can be accessed at http://localhost:9411

## TODO

- implement another service that stores reviews about the book and those reviews will be queryable
- implement author-client
- implement grapgql subscription

## References

- https://graphql.org/learn
- https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
- https://www.baeldung.com/spring-graphql