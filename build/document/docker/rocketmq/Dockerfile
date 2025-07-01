FROM openjdk:8u332
COPY /rocketmq-dashboard-2.0.1-SNAPSHOT.jar rocketmq-dashboard-2.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=""
ENTRYPOINT java ${JAVA_OPTS} -jar rocketmq-dashboard-2.0.1-SNAPSHOT.jar

# 根目录下执行 docker build -t rocketmq-dashboard:2.0.1-SNAPSHOT .
