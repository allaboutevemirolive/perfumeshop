FROM openjdk:21-jdk-slim

WORKDIR /app

# Check if JAR exists in target, copy if so
RUN if [ -f target/*.jar ]; then \
        cp target/*.jar app.jar; \
    # If not in target, check current directory
    elif [ -f *.jar ]; then \
        cp *.jar app.jar; \
    # No JAR found
    else \
        echo "ERROR: No JAR file found in target or current directory!" && exit 1; \
    fi

EXPOSE 5432

CMD ["java", "-jar", "app.jar"]

