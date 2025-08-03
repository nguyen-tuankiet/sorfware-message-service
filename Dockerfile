# Build stage
FROM maven:3.9.11-amazoncorretto-21-debian AS build

WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Run Maven to clean and package the project
RUN mvn package -DskipTests

# Run stage
FROM amazoncorretto:21.0.8
WORKDIR /app

# Copy the built war file from the build stage
COPY --from=build /app/target/*.war app.war

# Run the application
ENTRYPOINT ["java", "-jar", "app.war"]





