FROM openjdk:17.0.2-jdk-slim-buster
EXPOSE 8081
ADD target/demoBoot-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]