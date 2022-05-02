FROM openjdk:11
EXPOSE 5050
ARG JAR_FILE=target/trello-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]