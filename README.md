# springboot-graphql-databases

## Goal

The goal of this project is to explore [GraphQL](https://graphql.org). In order to do it, we will implement three
micro-services: `author-book-api`, `author-book-client` and `book-review-api`.

### author-book-api

Spring-boot application to handle authors and books. It exposes a GraphQL endpoint and traditional REST endpoints.
It uses [MySQL](https://www.mysql.com) as storage. It communicates with `book-review-api` to get book reviews. The
link between `author-book-api` and `book-review-api` is the `ISBN` of the book.

### book-review-api

Spring-boot application to handle books and their reviews. It only exposes a GraphQL endpoint and uses
[MongoDB](https://www.mongodb.com) as storage.

### author-book-client

Spring-boot application that consumes `author-book-api` and display the information in an user friendly interface
implemented using [Thymeleaf](https://www.thymeleaf.org).

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

### book-review-api

- access http://localhost:8081/graphiql

- create book and return its id
```
mutation {
  createBook(bookInput: {title: "Introdution to GraphQL", isbn: "123"}) {
    id
  }
}
```

- add one review for the book created above, suppose the id is `5bd4bd4790e9f641b7388f23`
```
mutation {
  addBookReview(bookId: "5bd4bd4790e9f641b7388f23", reviewInput: {reviewer: "Ivan Franchin", comment: "It is a very good book", rating: 8}) {
    id
  }
}
```

- get all books stored in book-review-api, including their reviews
```
{
  getAllBooks {
    id
    title
    isbn
    reviews {
      comment
      rating
      reviewer
    }
  }
}
```

### author-book-api

- access http://localhost:8080/graphiql

- create author and return its id
```
mutation {
  createAuthor(authorInput: {firstName: "Ivan", lastName: "Franchin"}) {
    id
  }
}
```

- create book and return the book id and author's first and last name
```
mutation {
  createBook(bookInput: {authorId: 1, isbn: "123", title: "Introdution to GraphQL", year: 2018, numPages: 512}) {
    id
    author {
      firstName
      lastName
    }
  }
}
```

- get author by id and return some information about his/her books including reviews of his/her book stored in `book-review-api`
```
{
  getAuthorById(authorId: 1) {
    firstName
    lastName
    books {
      isbn
      title
      reviews {
        rating
        comment
      }
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

- implement hystrix fallback in case book-review-api is down (http://nphumbert.github.io/blog/2017/07/23/setup-a-circuit-breaker-with-hystrix)
- add hystrix dashboard
- implement author-book-client
- implement graphql subscription

## References

- https://graphql.org/learn
- https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
- https://www.baeldung.com/spring-graphql