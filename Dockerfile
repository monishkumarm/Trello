FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} trello.jar
EXPOSE 5050
ENTRYPOINT ["java","-jar","trello.jar"]