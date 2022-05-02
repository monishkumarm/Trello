FROM openjdk:11
EXPOSE 5050
COPY ./target/trello-0.0.1-SNAPSHOT-jar-with-dependencies.jar ./
WORKDIR ./
CMD ["java", "-jar", "trello-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
