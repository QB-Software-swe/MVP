# Build
FROM maven:3-eclipse-temurin-21-alpine as Build
WORKDIR /mvp
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Image
FROM openjdk:21
WORKDIR /mvp
COPY --from=Build /mvp/target/demo_jmap_server-*-jar-with-dependencies.jar mvp.jar
CMD ["java", "-jar", "mvp.jar"]