FROM openjdk:11
EXPOSE 8089
ADD ./target/devsecops.jar devsecops.jar
ENTRYPOINT ["java","-jar","/devsecops.jar"]