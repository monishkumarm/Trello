FROM openjdk:11
EXPOSE 5050
WORKDIR /applications
COPY --from=build /workspace/target/*.jar trello.jar
ENTRYPOINT ["java","-jar", "trello.jar"]
