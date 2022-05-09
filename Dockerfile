FROM maven AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:11
COPY --from=build /workspace/target/*.jar trello.jar
EXPOSE 5050
ENTRYPOINT ["java","-jar","trello.jar"]
