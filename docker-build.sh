#!/usr/bin/env bash

./mvnw clean compile jib:dockerBuild --projects author-book-api
./mvnw clean compile jib:dockerBuild --projects book-review-api
