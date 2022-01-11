# syntax=docker/dockerfile:1
FROM openjdk:16-alpine3.13
MAINTAINER Raja Sekhar <gguthula@lululemon.com>
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean install
RUN javac /app/src/main/java/org/example/App.java
CMD ["java", "/app/src/main/java/org/example/Ap"]