FROM openjdk:17
WORKDIR /app
EXPOSE 8080
ADD target/bookcrossing-rest-1.0.0.jar bookcrossing-rest-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/app/bookcrossing-rest-1.0.0.jar"]