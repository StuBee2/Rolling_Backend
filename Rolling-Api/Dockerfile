FROM openjdk:17-jdk-alpine

COPY build/libs/Rolling-Api-0.0.1-SNAPSHOT.jar api.jar

ENTRYPOINT ["java", "-jar", "api.jar", "-x", "test"]