FROM openjdk:17-jdk-alpine

COPY build/libs/rolling-batch-0.0.1-SNAPSHOT.jar batch.jar

ENTRYPOINT ["java", "-jar", "batch.jar", "-x", "test"]