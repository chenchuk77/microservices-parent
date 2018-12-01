FROM openjdk:8-jdk-alpine
VOLUME /tmp

# injecting maven build-args into the container env
ARG ARTIFACT_ID
ARG GROUP_ID
ARG LMS_VERSION
ENV ARTIFACT_ID=${ARTIFACT_ID} GROUP_ID=${GROUP_ID} LMS_VERSION=${LMS_VERSION}

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","net.kukinet.backend.BootBackend"]
