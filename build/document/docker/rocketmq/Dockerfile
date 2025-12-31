FROM openjdk:8u332
COPY /rocketmq-dashboard-2.1.0.jar rocketmq-dashboard-2.1.0.jar
ENV JAVA_OPTS=""
ENTRYPOINT java ${JAVA_OPTS} -jar rocketmq-dashboard-2.1.0.jar

# 根目录下执行 docker build -t rocketmq-dashboard:2.1.0 .
