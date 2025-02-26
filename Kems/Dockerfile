FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/kems-ecommerce-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
