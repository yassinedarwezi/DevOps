FROM openjdk:11
EXPOSE 8089
ARG JAR_FILE=target/*1.0.jar
COPY ${JAR_FILE} back.jar
ENTRYPOINT ["java","-jar","/back.jar"]
