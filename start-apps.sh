#!/usr/bin/env bash

echo
echo "Starting author-book-api..."

docker run -d --rm --name author-book-api -p 8080:8080 \
  -e MYSQL_HOST=mysql -e ZIPKIN_HOST=zipkin -e BOOK_REVIEW_API_HOST=book-review-api -e SPRING_DATASOURCE_USERNAME=authorbookuser -e SPRING_DATASOURCE_PASSWORD=authorbookpass \
  --network=springboot-graphql-databases_default \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" --health-start-period=40s \
  ivanfranchin/author-book-api:1.0.0

echo
echo "Starting book-review-api..."

docker run -d --rm --name book-review-api -p 9080:9080 \
  -e MONGODB_HOST=mongodb -e ZIPKIN_HOST=zipkin -e SPRING_DATA_MONGODB_USERNAME=bookreviewuser -e SPRING_DATA_MONGODB_PASSWORD=bookreviewpass \
  --network=springboot-graphql-databases_default \
  --health-cmd="curl -f http://localhost:9080/actuator/health || exit 1" --health-start-period=40s \
  ivanfranchin/book-review-api:1.0.0
