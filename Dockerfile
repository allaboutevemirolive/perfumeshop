FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 5432

CMD ["java", "-jar", "app.jar"]

