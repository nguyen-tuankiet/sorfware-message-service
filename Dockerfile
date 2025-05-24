# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Run Maven to clean and package the project
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built war file from the build stage
COPY --from=build /app/target/sorfware-0.0.1-SNAPSHOT.war app.war

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.war"]





