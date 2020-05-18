FROM maven:3.6.3 AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
ARG MAIL_HOST
ARG MAIL_PORT
ARG MAIL_USERNAME
ARG MAIL_PASSWORD
ARG MAIL_PTL
ARG MAIL_PTL_EBLE
ARG MAIL_CNT_TIMEOUT

RUN mvn -f /usr/app/pom.xml -DMAIL_HOST=${MAIL_HOST} -DMAIL_PORT=${MAIL_PORT} -DMAIL_USERNAME=${MAIL_USERNAME} -DMAIL_PASSWORD=${MAIL_PASSWORD} -DMAIL_PTL=${MAIL_PTL} -DMAIL_PTL_EBLE=${MAIL_PTL_EBLE} -DMAIL_CNT_TIMEOUT=${MAIL_CNT_TIMOEUT} clean install

FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine
COPY --from=build /usr/app/target/music-all-back.jar /usr/local/lib/music-all-back.jar
WORKDIR /usr/app

ENTRYPOINT ["java","-jar","/usr/local/lib/music-all-back.jar"]