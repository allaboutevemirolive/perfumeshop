FROM openjdk:21-jdk-slim

WORKDIR /app

# Check if JAR exists in target, copy if so
COPY target/*.jar app.jar
# COPY *.jar app.jar

EXPOSE 5432

CMD ["java", "-jar", "app.jar"]

