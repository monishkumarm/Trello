FROM openjdk:11
ARG PROFILE
ENV PROFILE_VAR=$PROFILE
VOLUME /tmp
## Add the built jar for docker image building
ADD target/trello-0.0.1-SNAPSHOT.jar trello-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/bin/bash", "-c", "java","-Dspring.profiles.active=$PROFILE_VAR","-jar","/trello-0.0.1-SNAPSHOT.jar"]
## DO NOT USE(The variable would not be substituted): ENTRYPOINT ["java","-Dspring.profiles.active=$PROFILE_VAR","-jar","/trello-0.0.1-SNAPSHOT.jar"]
## CAN ALSO USE: ENTRYPOINT java -Dspring.profiles.active=$PROFILE_VAR -jar /trello-0.0.1-SNAPSHOT.jar
EXPOSE 5050