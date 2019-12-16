FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY application.properties .
ARG JAR_FILE=goci-interfaces/goci-rest/target/goci-rest-?.?.?-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]