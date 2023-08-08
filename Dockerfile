# https://dev.to/lucasleon/heroku-is-not-free-anymore-so-ill-teach-you-how-to-deploy-your-spring-boot-services-to-rendercom-with-maven-and-docker-aca

# Build Stage
FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x ./mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Final Stage
FROM openjdk:8-jdk-alpine

# Update package lists and install curl
RUN apk --no-cache add curl

# Set up volumes and arguments
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

# Copy dependencies and classes
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Copy the .sh script to the container's working directory
COPY simplePinger.sh /simplePinger.sh

# Make the Bash script executable
RUN chmod +x /simplePinger.sh

# Entry Point for CRUD Application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.deanofwalls.CRUD_DEMO.MainApplication"]

# Optionally, you can run the Bash script when the container starts
# CMD ["/simplePinger.sh"]
