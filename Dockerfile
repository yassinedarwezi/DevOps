FROM openjdk:11
EXPOSE 8089
ADD ./target/tpAchatProject.jar devsecops.jar
ENTRYPOINT ["java","-jar","/devsecops.jar"]