# Use an image with Java 17
FROM openjdk:8-jdk-alpine as build1
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x ./mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Use Java 8 as the base image for the final stage
FROM openjdk:8-jdk-alpine as build2
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build1 ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build1 ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build1 ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.github.deanOfWalls.CRUD_demo"]
