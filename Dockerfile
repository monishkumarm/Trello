FROM openjdk:11
EXPOSE 5050
ARG JAR_FILE=target/trello-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} trello.jar
ENTRYPOINT ["java","-jar","trello.jar"]