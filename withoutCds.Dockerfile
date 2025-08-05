FROM gradle AS builder
WORKDIR /builder
COPY . .
RUN gradle build --no-daemon

FROM bellsoft/liberica-openjre-debian:24-cds AS builder-cds
WORKDIR /builder
COPY --from=builder /builder/build/libs/ultimoguia-1.0.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted


FROM bellsoft/liberica-openjre-debian:24-cds
WORKDIR /application
COPY --from=builder-cds /builder/extracted/dependencies/ ./
COPY --from=builder-cds /builder/extracted/spring-boot-loader/ ./
COPY --from=builder-cds /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder-cds /builder/extracted/application/ ./
ENTRYPOINT ["java", "-jar", "application.jar"]
