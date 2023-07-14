FROM openjdk:11

WORKDIR /app

COPY shopsmart-0.0.1-SNAPSHOT.jar /app/shopsmart.jar

EXPOSE 8081

CMD ["java", "-jar", "shopsmart.jar"]
