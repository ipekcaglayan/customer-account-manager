# Dockerfile for Transaction Management Service

# Use the official Maven image for the build stage with Java 21 (Eclipse Temurin)
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and install dependencies (will be cached)
COPY pom.xml .

# Copy source files into the container
COPY src ./src

# Build the project, skipping tests for faster builds
RUN mvn clean package -DskipTests

# Use the official Eclipse Temurin OpenJDK image for running the app
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Expose the port the Spring Boot application will run on
EXPOSE 8088

# Copy the built jar file from the build stage
COPY --from=build /app/target/transactionmanagement-*.jar app.jar

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
