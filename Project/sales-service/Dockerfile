FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-sales.jar
ENTRYPOINT ["java","-jar","/service-sales.jar"]
EXPOSE 9191