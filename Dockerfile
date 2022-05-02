FROM openjdk:11
EXPOSE 5050
COPY target/*.jar trello.jar
ENTRYPOINT ["java","-jar", "trello.jar"]
