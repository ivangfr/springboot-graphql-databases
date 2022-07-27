#!/usr/bin/env bash

source scripts/my-functions.sh

echo
echo "Starting author-book-api ..."

docker run -d --rm --name author-book-api -p 8080:8080 \
  -e MYSQL_HOST=mysql -e ZIPKIN_HOST=zipkin -e BOOK_REVIEW_API_HOST=book-review-api -e SPRING_DATASOURCE_USERNAME=authorbookuser -e SPRING_DATASOURCE_PASSWORD=authorbookpass \
  --network=springboot-graphql-databases_default \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" \
  ivanfranchin/author-book-api:1.0.0

wait_for_container_log "author-book-api" "Started"

echo
echo "Starting book-review-api ..."

docker run -d --rm --name book-review-api -p 9080:9080 \
  -e MONGODB_HOST=mongodb -e ZIPKIN_HOST=zipkin -e SPRING_DATA_MONGODB_USERNAME=bookreviewuser -e SPRING_DATA_MONGODB_PASSWORD=bookreviewpass \
  --network=springboot-graphql-databases_default \
  --health-cmd="curl -f http://localhost:9080/actuator/health || exit 1" \
  ivanfranchin/book-review-api:1.0.0

wait_for_container_log "book-review-api" "Started"

printf "\n"
printf "%15s | %8s | %43s |\n" "Application" "URL Type" "URL"
printf "%15s + %8s + %43s |\n" "---------------" "--------" "-------------------------------------------"
printf "%15s | %8s | %43s |\n" "author-book-api" "Swagger" "http://localhost:8080/swagger-ui/index.html"
printf "%15s | %8s | %43s |\n" "author-book-api" "GraphiQL" "http://localhost:8080/graphiql"
printf "%15s | %8s | %43s |\n" "book-review-api" "GraphiQL" "http://localhost:9080/graphiql"
printf "\n"
