FROM amazoncorretto:21.0.4-alpine3.18
ARG JAR_FILE=target/franquiciasApp-0.0.1-SNAPSHOT.jar
WORKDIR /app
EXPOSE 8080
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "/app/app.jar"]
