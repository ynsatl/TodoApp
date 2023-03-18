FROM openjdk:19-jdk-alpine
COPY target/TodoApp-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]

