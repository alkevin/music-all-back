FROM maven:3.6.3 AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
ARG MAIL_HOST
ENV ML_HT=${MAIL_HOST}
ARG MAIL_PORT
ENV ML_PRT=${MAIL_PORT}
ARG MAIL_USERNAME
ENV ML_UE=${MAIL_USERNAME}
ARG MAIL_PASSWORD
ENV ML_PD=${MAIL_PASSWORD}
ARG MAIL_PLT
ENV ML_PT=${MAIL_PLT}
ARG MAIL_PLT_ENBLE
ENV ML_PLT_EE=${MAIL_PLT_ENBLE}
ARG MAIL_CNT_TIMEOUT
ENV ML_CNT_TT=${MAIL_CNT_TIMEOUT}

RUN mvn -f /usr/app/pom.xml -DMAIL_HOST=ML_HT -DMAIL_PORT=ML_PT -DMAIL_USERNAME=ML_UE -DMAIL_PASSWORD=ML_PD -DMAIL_PTL=ML_PTL -DMAIL_PTL_EBLE=ML_PTL_EE -DMAIL_CNT_TMOUT=ML_CNT_TT clean install

FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine
COPY --from=build /usr/app/target/music-all-back.jar /usr/local/lib/music-all-back.jar
WORKDIR /usr/app

ENTRYPOINT ["java","-jar","/usr/local/lib/music-all-back.jar"]