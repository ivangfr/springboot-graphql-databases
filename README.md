# `springboot-graphql-databases`

The goal of this project is to explore [GraphQL](https://graphql.org). For it, we will implement three microservices:
`author-book-api`, `author-book-client` and `book-review-api`.

# Microservices

![project-diagram](images/project-diagram.png)

## author-book-api

Spring-boot Web Java application that handles authors and books. It exposes a GraphQL endpoint **and** traditional REST
API endpoints. `author-book-api` uses [MySQL](https://www.mysql.com) as storage and calls `book-review-api` to get the
reviews of the books. It uses [Feign](https://github.com/OpenFeign/feign) to easily create a client for `book-review-api`
and [Hystrix](https://github.com/Netflix/Hystrix) (latency and fault tolerance library) to handle situations when
`book-review-api` is down. The book `ISBN` is what connects books stored in `author-book-api` with the ones stored in
`book-review-api`.

## book-review-api

Spring-boot Web Java application that handles books and their reviews. It only exposes a GraphQL API and uses
[MongoDB](https://www.mongodb.com) as storage.

## author-book-client

TODO

# Build Docker Images

In a terminal and inside `springboot-graphql-databases` root folder, run the following `./mvnw` commands to build the
microservices docker images

## author-book-api
```bash
./mvnw clean package dockerfile:build -DskipTests --projects author-book-api
```
| Environment Variable | Description |
| -------------------- | ------------- |
| `MYSQL_HOST` | Specify host of the `MySQL` database to use (default `localhost`) |
| `MYSQL_PORT` | Specify port of the `MySQL` database to use (default `3306`) |
| `ZIPKIN_HOST` | Specify host of the `Zipkin` distributed tracing system to use (default `localhost`) |
| `ZIPKIN_PORT` | Specify port of the `Zipkin` distributed tracing system to use (default `9411`) |
| `BOOK_REVIEW_API_HOST` | Specify host of the `book-review-api` service (default `localhost`) |
| `BOOK_REVIEW_API_PORT` | Specify port of the `book-review-api` service (default `8080`) |

## book-review-api
```bash
./mvnw clean package dockerfile:build -DskipTests --projects book-review-api
```
| Environment Variable | Description |
| -------------------- | ------------- |
| `MONGODB_HOST` | Specify host of the `MongoDB` database to use (default `localhost`) |
| `MONGODB_PORT` | Specify port of the `MongoDB` database to use (default `27017`) |
| `ZIPKIN_HOST` | Specify host of the `Zipkin` distributed tracing system to use (default `localhost`) |
| `ZIPKIN_PORT` | Specify port of the `Zipkin` distributed tracing system to use (default `9411`) |

## author-book-client

TODO

# Start Environment

- Open one terminal

- In `springboot-graphql-databases` root folder run
```bash
docker-compose up -d
```
> To stop and remove containers, networks and volumes
>```bash
>docker-compose down -v
>```

- Wait a little bit until all containers are Up (healthy). You can check their status running
```bash
docker-compose ps
```

# Microservice Links

| Microservice | URL Type | URL |
| ------------ | -------- | --- |
| author-book-api | Swagger | http://localhost:8080/swagger-ui.html |
| author-book-api | GraphiQL | http://localhost:8080/graphiql |
| book-review-api | GraphiQL | http://localhost:8081/graphiql |
| author-book-client | Website | TODO |

# Running microservices with Maven

During development, it is easy to just run the microservices instead of always build their docker images before running
them. In order to do that, comment the microservice(s) in `docker-compose.yml` file (so that they do not start when you
start the environment) and run them with Maven.

### author-book-api
```bash
export BOOK_REVIEW_API_PORT=8081
./mvnw spring-boot:run --projects author-book-api
```

### book-review-api
```bash
./mvnw spring-boot:run --projects book-review-api -Dspring-boot.run.jvmArguments="-Dserver.port=8081"
```

### author-book-client

TODO

# How to use GraphiQL

## book-review-api

- In a browser, access the url http://localhost:8081/graphiql

- Create a book and return its id
```
mutation {
  createBook(bookInput: {title: "Introdution to GraphQL", isbn: "123"}) {
    id
  }
}
```

- Add one review for the book created above, suppose the id is `5bd4bd4790e9f641b7388f23`
```
mutation {
  addBookReview(bookId: "5bd4bd4790e9f641b7388f23", reviewInput: {reviewer: "Ivan Franchin", comment: "It is a very good book", rating: 8}) {
    id
  }
}
```

- Get all books stored in book-review-api, including their reviews
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

## author-book-api

- In a browser, access the url http://localhost:8080/graphiql

- Create an author and return its id
```
mutation {
  createAuthor(authorInput: {firstName: "Ivan", lastName: "Franchin"}) {
    id
  }
}
```

- Create a book and return the book id and author's first and last name

> **Note.** while creating this book in `author-book-api`, we are setting the same ISBN, `123`, as we did when 
creating the book in `book-review-api`.
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

- Get author by id and return some information about his/her books including reviews of the book stored in
`book-review-api`.

> **Note.** as the book stored in `author-book-api` and `book-review-api` has the same ISBN, `123`, it's possible to
retrieve the reviews of the book. Otherwise, an empty list will be returned in case `book-review-api` does not have a
specific ISBN or the service is down. 
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

- Update book title and return its id and new title
```
mutation {
  updateBook(bookId: 1, bookInput: {title: "GraphQL in a Nutshell"}) {
    id
    title
  }
}
```

- Delete author and return his/her id
```
mutation {
  deleteAuthor(authorId: 1) {
    id
  }
}
```

# Useful links & commands

## Hystrix Dashboard

- It can be accessed at http://localhost:8080/hystrix

- Add `http://localhost:8080/actuator/hystrix.stream` to the input field.

## MySQL monitor
```
docker exec -it mysql mysql -uroot -psecret --database=authorbookdb
show tables;
select * from authors;
select * from books;
```
> Type `exit` to get out of MySQL monitor

## MongoDB shell
```
docker exec -it mongodb mongo
use bookreviewdb;
db.books.find().pretty();
```
> Type `exit` to get out of MongoDB shell

# TODO

- replace `Hystrix` by `Resilience4j`;
- implement `author-book-client`;
- study how to implement authentication/authorization to `GraphQL` endpoint;
- implement `graphql` subscription;

# References

- https://graphql.org/learn
- https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot
- https://www.baeldung.com/spring-graphql