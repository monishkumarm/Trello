FROM openjdk:11
EXPOSE 5050
WORKDIR /applications
COPY target/*.jar trello.jar
ENTRYPOINT ["java","-jar", "trello.jar"]
