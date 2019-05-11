FROM java:8
MAINTAINER bupt.coder
ADD build/libs/notice-microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# spring.profiles.active=create
# spring.profiles.active=upfate