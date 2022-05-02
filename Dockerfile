FROM openjdk:11
EXPOSE 5050
COPY target/trello-0.0.1-SNAPSHOT.jar trello.jar
ENTRYPOINT ["java","-jar", "trello.jar"]