# FROM openjdk:21-jdk-slim
# WORKDIR /app
# COPY target/kems-ecommerce-1.0.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "app.jar"]


# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the target directory
COPY target/kems-ecommerce-1.0.jar app.jar

# Expose the application port
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

