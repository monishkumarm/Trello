FROM openjdk:11
EXPOSE 5050
ADD target/trello-0.0.1-SNAPSHOT.jar trello-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/trello-0.0.1-SNAPSHOT.jar"]