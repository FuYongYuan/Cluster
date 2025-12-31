FROM openjdk:21-slim
COPY /sentinel-dashboard-1.8.9.jar sentinel-dashboard-1.8.9.jar
ENV JAVA_OPTS="-Dserver.port=8948 \
-Dcsp.sentinel.dashboard.server=localhost:8948 \
-Dsentinel.dashboard.auth.username=fyy \
-Dsentinel.dashboard.auth.password=fyyain77"
ENTRYPOINT java ${JAVA_OPTS} -jar sentinel-dashboard-1.8.9.jar

# 根目录下执行 docker build -t sentinel-dashboard:1.8.9 .

# 根目录下执行 docker run --name sentinel-dashboard-1.8.9 -p 8948:8948 -d sentinel-dashboard:1.8.9