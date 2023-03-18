FROM arm64v8/openjdk:19
COPY target/TodoApp-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]

