FROM openjdk:17-jdk-slim

WORKDIR /app

COPY out/artifacts/ProductAPI_jar/ProductAPI.jar app.jar

CMD ["java", "-Dserver.port=8080", "-jar", "app.jar"]