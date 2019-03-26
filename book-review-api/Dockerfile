FROM openjdk:8u191-jdk-alpine

LABEL maintainer="ivangfr@yahoo.com.br"

ARG JAR_FILE
COPY ${JAR_FILE} /app.jar

CMD ["java", "-jar", "/app.jar"]

EXPOSE 8080
