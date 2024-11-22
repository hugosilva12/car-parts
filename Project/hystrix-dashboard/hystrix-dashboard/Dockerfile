FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} histrix.jar
ENTRYPOINT ["java","-jar","/histrix.jar"]
EXPOSE 9191