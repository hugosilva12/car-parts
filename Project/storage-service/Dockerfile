FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-storage.jar
ENTRYPOINT ["java","-jar","/service-storage.jar"]
EXPOSE 9191