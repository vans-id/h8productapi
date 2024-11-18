FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/productApi-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-Dserver.port=8080", "-jar", "app.jar"]