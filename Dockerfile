FROM amazoncorretto:17
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
COPY .env /app/.env
CMD ["java", "-jar", "app.jar"]