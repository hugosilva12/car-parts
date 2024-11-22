FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} purchase-service.jar
ENTRYPOINT ["java","-jar","/purchase-service.jar"]
EXPOSE 9191