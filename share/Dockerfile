FROM maven:3.5.4-jdk-8-alpine
MAINTAINER share
ADD ./ /share
RUN cd /share && mvn package
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n","-jar","/share/target/sharingMicroService-0.0.1.jar"]