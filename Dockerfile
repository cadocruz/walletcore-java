FROM alpine:latest AS build
ENV JAVA_HOME /opt/jdk/jdk-21.0.3+9

ENV PATH $JAVA_HOME/bin:$PATH

ADD https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.3%2B9/OpenJDK21U-jdk_x64_alpine-linux_hotspot_21.0.3_9.tar.gz /opt/jdk/
RUN tar -xzvf /opt/jdk/OpenJDK21U-jdk_x64_alpine-linux_hotspot_21.0.3_9.tar.gz -C /opt/jdk/

RUN ["jlink", "--compress=2", \
          "--add-modules", "java.base", \
          "--no-header-files", "--no-man-pages", \
          "--output", "/springboot-runtime"]

FROM alpine:latest
COPY --from=build  /springboot-runtime /opt/jdk
ENV PATH=$PATH:/opt/jdk/bin

WORKDIR /app

COPY target/walletcore-0.0.1-SNAPSHOT.jar application.jar

EXPOSE 8443

CMD ["java", "-jar", "application.jar", "--spring.profiles.active=prod"]
