# Build stage
FROM maven:3-openjdk-21 AS build

WORKDIR /app

# Copy the project files
COPY . .

# Run Maven to clean and package the project
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the built war file from the build stage
COPY --from=build /app/target/DrComputer-0.0.1-SNAPSHOT.war drcomputer.war

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "drcomputer.war"]
