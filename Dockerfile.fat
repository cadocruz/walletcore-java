FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/walletcore-0.0.1-SNAPSHOT.jar application.jar

EXPOSE 8443

CMD ["java", "-jar", "application.jar", "--spring.profiles.active=prod"]
