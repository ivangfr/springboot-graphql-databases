#!/usr/bin/env bash

DOCKER_IMAGE_PREFIX="ivanfranchin"
APP_VERSION="1.0.0"

AUTHOR_BOOK_API_APP_NAME="author-book-api"
BOOK_REVIEW_API_APP_NAME="book-review-api"

AUTHOR_BOOK_API_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${AUTHOR_BOOK_API_APP_NAME}:${APP_VERSION}"
BOOK_REVIEW_API_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${BOOK_REVIEW_API_APP_NAME}:${APP_VERSION}"

SKIP_TESTS="true"

./mvnw clean spring-boot:build-image \
  --projects "$AUTHOR_BOOK_API_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$AUTHOR_BOOK_API_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$BOOK_REVIEW_API_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$BOOK_REVIEW_API_DOCKER_IMAGE_NAME"
