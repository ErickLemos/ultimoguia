FROM gradle AS builder
WORKDIR /builder

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon

COPY . .
RUN gradle build --no-daemon

FROM bellsoft/liberica-runtime-container:jdk-all-21-cds-musl AS builder-cds
WORKDIR /builder
COPY --from=builder /builder/build/libs/ultimoguia-1.0.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM bellsoft/liberica-runtime-container:jre-21-cds-musl
WORKDIR /application
EXPOSE 8080

COPY --from=builder-cds /builder/extracted/dependencies/ ./
COPY --from=builder-cds /builder/extracted/spring-boot-loader/ ./
COPY --from=builder-cds /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder-cds /builder/extracted/application/ ./

RUN java -XX:ArchiveClassesAtExit=application.jsa -Dspring.context.exit=onRefresh -jar application.jar
ENTRYPOINT [ \
    "java", \
    "-XX:+UseG1GC", \
    "-XX:MaxRAMPercentage=70", \
    "-XX:SharedArchiveFile=application.jsa", \
    "-XX:StartFlightRecording=name=ContinuousRecording,settings=profile,filename=/recordings/app.jfr", \
    "-jar", \
    "application.jar" \
]