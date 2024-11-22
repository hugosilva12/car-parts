FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-cardisassembly.jar
ENTRYPOINT ["java","-jar","/service-cardisassembly.jar"]
EXPOSE 9191