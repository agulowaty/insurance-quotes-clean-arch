FROM gradle:jdk8 as builder

COPY --chown=gradle . /app

WORKDIR /app

RUN ./gradlew build --stacktrace

COPY /build/libs/insurance-quotes-clean-arch-*.jar /dist/boot.jar


FROM java:8-jre-alpine

COPY --from=builder /dist/boot.jar .

CMD ["java", "-jar", "boot.jar"]

EXPOSE 8080
