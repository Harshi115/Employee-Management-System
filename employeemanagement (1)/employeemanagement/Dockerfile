# Use Java 17 Alpine (smaller image)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper files
COPY .mvn/ .mvn/
COPY mvnw ./
COPY pom.xml ./

# Make mvnw executable
RUN chmod +x ./mvnw

# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/employeemanagement-0.0.1-SNAPSHOT.jar"]
