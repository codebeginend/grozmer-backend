FROM openjdk:8-jdk-alpine

LABEL maintainer="elbukaevzaur@gmail.com"

EXPOSE 8081

ARG JAR_FILE=target/grozmer.jar

ADD ${JAR_FILE} grozmer.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker","-jar","/grozmer.jar"]
