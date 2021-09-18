FROM openjdk:12-jdk-alpine

RUN addgroup -S company-service && adduser -S company-service -G company-service

ENV HOME_DIRECTORY="/home/company-service"
ENV LOGS_DIRECTORY="$HOME_DIRECTORY/logs"

USER company-service

RUN mkdir $LOGS_DIRECTORY

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","org.miniats.companyservice.CompanyServiceApplication"]