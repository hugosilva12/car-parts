FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-precarious.jar
ENTRYPOINT ["java","-jar","/service-precarious.jar"]
EXPOSE 9191