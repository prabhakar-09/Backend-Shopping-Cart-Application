 FROM openjdk:17
 
 EXPOSE 8761
 
 ADD target/service-registry.jar service-registry.jar
 # ARG JAR_FILE=target/*.jar
 
 # COPY ${JAR_FILE} serviceregistry.jar
 
 ENTRYPOINT ["java", "-jar", "/service-registry.jar"]
 
 