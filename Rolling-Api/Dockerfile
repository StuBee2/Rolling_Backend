FROM openjdk:17-jdk-alpine

COPY build/libs/Rolling-Api-1.0.jar api.jar

ENTRYPOINT ["java", "-jar", "api.jar", "-x", "test"]