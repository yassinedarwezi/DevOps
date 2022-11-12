FROM openjdk:11
EXPOSE 8089
ADD ./target/tpAchatProject-1.0.3-SNAPSHOT.jar devsecops.jar
ENTRYPOINT ["java","-jar","/devsecops.jar"]