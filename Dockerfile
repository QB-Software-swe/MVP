# Build
FROM maven:3-eclipse-temurin-21-alpine as Build
WORKDIR /poc
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Image
FROM openjdk:21
WORKDIR /poc
COPY --from=Build /poc/target/poc-*-jar-with-dependencies.jar poc.jar
CMD ["java", "-jar", "poc.jar"]