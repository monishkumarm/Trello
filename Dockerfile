FROM openjdk
COPY ./target/trello-1.0-SNAPSHOT-jar-with-dependencies.jar ./
WORKDIR ./
CMD ["java", "-jar", "trello-1.0-SNAPSHOT-jar-with-dependencies.jar"]
