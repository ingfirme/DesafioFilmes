FROM openjdk:17-ea-5-jdk-alpine
MAINTAINER Ingrid
VOLUME /main-app
ADD target/ProjetoIngrid-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]