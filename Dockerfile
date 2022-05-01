FROM openjdk
COPY ./target/Trello-1.0-SNAPSHOT-jar-with-dependencies.jar ./
WORKDIR ./
CMD ["java", "-jar", "Trello-1.0-SNAPSHOT-jar-with-dependencies.jar"]
