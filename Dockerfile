FROM openjdk:11
ARG PROFILE
RUN echo "here is the build env: $PROFILE!"