# Stage 1: Build the application
FROM gradle:8.3.0-jdk17 as build

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and project files
COPY build.gradle settings.gradle gradle /app/
# Download dependencies
RUN gradle build -x test --no-daemon || return 0

# Copy the entire project
COPY . .

# Build the application
RUN gradle bootJar -x test --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
