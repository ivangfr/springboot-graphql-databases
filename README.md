# springboot-graphql-databases

## Goal

The goal of this project is to explore [GraphQL](https://graphql.org). In order to do it, we will implement three micro-services: `author-book-api`, `author-book-client` and `book-review-api`.

### author-book-api

Spring-boot application to handle authors and books. It exposes a GraphQL endpoint and traditional REST endpoints. It uses [MySQL](https://www.mysql.com) as storage.

### author-book-client

Spring-boot application that consumes `author-book-api` and display the information in an user friendly interface implemented using [Thymeleaf](https://www.thymeleaf.org).

### book-review-api

Spring-boot application to handle books and their reviews. It only exposes a GraphQL endpoint and uses [MongoDB](https://www.mongodb.com) as storage.

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

### author-book-api

- Open a new terminal
- Inside `/springboot-graphql-databases/author-book-api` folder run
```
mvn spring-boot:run
```

#### Rest API

- The link for author-book-api `Swagger` web page is: http://localhost:8080/swagger-ui.html

#### GraphQL

- The link for author-book-api `GraphiQL` web page is: http://localhost:8080/graphiql

### book-review-api

- Open a new terminal
- Inside `/springboot-graphql-databases/book-review-api` folder run
```
mvn spring-boot:run
```

#### GraphQL

- The link for book-review-api `GraphiQL` web page is: http://localhost:8081/graphiql

### author-book-client

- Open a new terminal
- Inside `/springboot-graphql-databases/author-book-client` folder run
```
mvn spring-boot:run
```
- The link for author-book-client web page is http://localhost:8082

## How to use GraphiQL

Bellow, there are some GraphQL queries and mutations to be used in `author-book-api`.

> We won't write GraphQL queries and mutations for `book-review-api` because they will be similar and easily writable using GraphiQL

- access http://localhost:8080/graphiql

- create author and return the id
```
mutation {
  createAuthor(authorInput: {firstName: "Ivan", lastName: "Franchin"}) {
    id
  }
}
```

- create book and return the id of the book, first and last name of the author
```
mutation {
  createBook(bookInput: {authorId: 1, isbn: "123", title: "C++", year: 2018, numPages: 512}) {
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
  getAuthor(authorId: 1) {
    id
    firstName
    lastName
    books {
      isbn
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
  updateBook(bookId: 2, bookInput: {title: "Java10"}) {
    id
    title
  }
}
```

- delete author and return his/her id
```
mutation {
  deleteAuthor(authorId: 1) {
    id
  }
}
```

### Useful links

- Zipkin can be accessed at http://localhost:9411

## TODO

- implement another service that stores reviews about the book and those reviews will be queryable
- implement author-book-client
- implement graphql subscription

## References

- https://graphql.org/learn
- https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
- https://www.baeldung.com/spring-graphql