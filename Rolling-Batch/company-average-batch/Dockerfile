FROM openjdk:17-jdk-alpine

COPY ../build/libs/company-average-batch-1.0.jar companyaveragebatch.jar

ENTRYPOINT ["java", "-jar", "companyaveragebatch.jar", "-x", "test"]