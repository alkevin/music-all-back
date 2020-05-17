FROM maven:3.6.3 AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
RUN mvn -f /usr/app/pom.xml mvn -DMAIL_HOST=${{secrets.MAIL_HOST}} -DMAIL_PORT=${{secrets.MAIL_PORT}} -DMAIL_USERNAME=${{secrets.MAIL_USERNAME}} -DMAIL_PASSWORD=${{secrets.MAIL_PASSWORD}} -DMAIL_PTL=${{secrets.MAIL_PTL}} -DMAIL_PTL_EBLE=${{secrets.MAIL_PTL_EBLE}} -DMAIL_CNT_TMOUT=${{secrets.MAIL_CNT_TMOUT}} clean install

FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine
COPY --from=build /usr/app/target/music-all-back.jar /usr/local/lib/music-all-back.jar
WORKDIR /usr/app

ENTRYPOINT ["java","-jar","/usr/local/lib/music-all-back.jar"]