# AKAI-Bookcrossing-REST
REST API for AKAI-Bookcrossing application. 
The project is based on [AKAI-Bookcrossing](https://github.com/akai-org/AKAI-bookcrossing)

## How to install with Docker and Intellij Ultimate
1. Download repo
2. Fill the file `application.properties` with proper environment variables
3. `docker-compose up` - to set up database
4. Run Configuration BookcrossingRestApplication (you need Java 17, which you can download by Intellij)
5. Ready. Thing is accessible at localhost:8080.

## How to install only with Docker
1. Download repo and uncomment part about Spring in `docker-compose.yml`
2. Fill the file `application.properties` with proper environment variables
3. `docker-compose up` - to set up database
4. `mvn clean install` - if you have installed Maven
5. `mvn spring-boot:run` - if you have installed Maven
6. Ready. Thing is accessible at localhost:8080.

If you don't have installed Maven instead of `mvn` use:
   - `./mvnw` - on Linux
   - `./mvnw.cmd` - on Windows
