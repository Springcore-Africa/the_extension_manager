# Multi-stage build to reduce image size
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app

# Copy Maven files and download dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copy source code and build the application
COPY src src
RUN ./mvnw package -DskipTests

# Final image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /workspace/app/target/*.jar app.jar

# Run as non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expose port 8080 (Render's default)
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]