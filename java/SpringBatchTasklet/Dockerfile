FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /SpringBatchTasklet
COPY --chown=maven:maven . /SpringBatchTasklet
RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-alpine
RUN mkdir "SpringBatchTasklet"
COPY --from=build /SpringBatchTasklet/target/*.jar /SpringBatchTasklet/
WORKDIR /SpringBatchTasklet
ENTRYPOINT ["java","-jar","SpringBatchTasklet-0.0.1-SNAPSHOT.jar"]
