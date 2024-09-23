# Build Stage
FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

# Copy Maven wrapper and other necessary files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Ensure Maven wrapper script is executable
RUN chmod +x ./mvnw

# Package the application without running tests
RUN ./mvnw package -DskipTests

# Final Stage - Creating a lean runtime image
FROM openjdk:8-jdk-alpine

# Set up volumes and arguments
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copy the JAR file built in the previous stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Expose the port the app runs on
# EXPOSE 8080

# Entry point for running the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app.jar"]
