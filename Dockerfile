FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} backend.jar
EXPOSE 5050
ENTRYPOINT ["java","-jar","/backend.jar"]