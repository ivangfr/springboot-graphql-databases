# springboot-graphql-databases

## Goal

The goal of this project is to explore [GraphQL](https://graphql.org). In order to do it, we will implement three
micro-services (discoverable by an `eureka-server`): `author-book-api`, `author-book-client` and `book-review-api`.

### author-book-api

Spring-boot application that handles authors and books. It exposes a GraphQL endpoint and traditional REST endpoints.
`author-book-api` uses [MySQL](https://www.mysql.com) as storage and communicates with `book-review-api` to get book
reviews. It's used [Feign](https://github.com/OpenFeign/feign) to create easily a client for `book-review-api` and
[Hystrix](https://github.com/Netflix/Hystrix) (latency and fault tolerance library) to deal with situations when
`book-review-api` is down. The book `ISBN` value is what connects books stored in `author-book-api` with the ones stored in
`book-review-api`.

### book-review-api

Spring-boot application that handles books and their reviews. It only exposes a GraphQL endpoint and uses
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
- The link for `eureka-server` is http://localhost:8761

### author-book-api

- Open a new terminal
- Inside `/springboot-graphql-databases/author-book-api` folder run
```
mvn spring-boot:run
```

#### Rest API

- The link for `author-book-api` **Swagger** web page is: http://localhost:8080/swagger-ui.html

#### GraphQL

- The link for `author-book-api` **GraphiQL** web page is: http://localhost:8080/graphiql

### book-review-api

- Open a new terminal
- Inside `/springboot-graphql-databases/book-review-api` folder run
```
mvn spring-boot:run
```

- The link for `book-review-api` **GraphiQL** web page is: http://localhost:8081/graphiql

### author-book-client

- Open a new terminal
- Inside `/springboot-graphql-databases/author-book-client` folder run
```
mvn spring-boot:run
```
- The link for `author-book-client` web page is http://localhost:8082

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

- get author by id and return some information about his/her books including reviews of his/her book stored in `book-review-api`.
P.S. as the book stored in `author-book-api` has ISBN `123` and the book stored in `book-review-api` has the same ISBN,
it is possible to retrieve reviews of the book. Otherwise, an empty list will be returned in case `book-review-api` does
not have a specific ISBN or the service is down. 
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

#### Hystrix Dashboard

- It can be accessed at http://localhost:8080/hystrix
- Add `http://localhost:8080/actuator/hystrix.stream` to the input field.

## TODO

- implement author-book-client
- implement graphql subscription

## References

- https://graphql.org/learn
- https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
- https://www.baeldung.com/spring-graphql