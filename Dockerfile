FROM openjdk:11
EXPOSE 5050
WORKDIR /applications
COPY target/trello-0.0.1-SNAPSHOT.jar /applications/trello.jar
ENTRYPOINT ["java","-jar", "trello.jar"]
