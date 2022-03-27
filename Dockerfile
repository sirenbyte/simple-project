FROM openjdk:8-jre-alpine
MAINTAINER Abzal <abzal.tugan@gmail.com>

ENV TZ=Asia/Almaty
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
VOLUME /tmp
ARG JAR_FILE
ADD ./target/demo11-0.0.1-SNAPSHOT.jar /app/
EXPOSE 80
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap","-jar","/app/demo11-0.0.1-SNAPSHOT.jar"]