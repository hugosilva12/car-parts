FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-auth.jar
ENTRYPOINT ["java","-jar","/service-auth.jar"]
EXPOSE 9191