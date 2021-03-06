FROM openjdk:8-jdk-alpine

LABEL maintainer="elbukaevzaur@gmail.com"

EXPOSE 8081

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} grozmer.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker","-jar","/grozmer.jar"]
