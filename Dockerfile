FROM maven:3.6.3-jdk-11-slim AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11-slim
COPY --from=build /usr/src/app/target/*.jar /usr/app/graphqldemo.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/graphqldemo.jar"]
