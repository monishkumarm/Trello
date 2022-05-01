FROM openjdk:11
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 5050
ENTRYPOINT ["java","-jar","app.jar"]
