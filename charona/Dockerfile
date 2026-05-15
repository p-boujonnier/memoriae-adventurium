# Étape 1 - Build
FROM gradle:jdk25 AS build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle bootJar --no-daemon

# Étape 2 - Run
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]