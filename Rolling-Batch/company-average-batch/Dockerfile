FROM openjdk:17-jdk-alpine

COPY ../build/libs/company-average-batch-0.0.1-SNAPSHOT.jar companyaveragebatch.jar

ENTRYPOINT ["java", "-jar", "companyaveragebatch.jar", "-x", "test"]