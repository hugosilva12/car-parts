FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-advertising.jar
ENTRYPOINT ["java","-jar","/service-advertising.jar"]
EXPOSE 9191