#!/usr/bin/env bash

./mvnw clean package dockerfile:build -DskipTests --projects author-book-api
./mvnw clean package dockerfile:build -DskipTests --projects book-review-api
