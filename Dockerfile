# This image uses 'archive' spring profile by default,
# which allows to have several rows in DB
FROM openjdk:8u171-jdk
VOLUME /tmp
ARG VERSION
COPY interview-${VERSION}.jar /product-viewer.jar
EXPOSE 8080
RUN bash -c 'touch /product-viewer.jar'
ENTRYPOINT exec java -jar product-viewer.jar --spring.profiles.active=default,archive