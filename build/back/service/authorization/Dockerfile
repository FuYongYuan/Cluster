# 构建docker镜像 docker build -t fuyy/cluster-build-authorization:0.0.1 .
# 将镜像推送dockerhub库 docker push fuyy/cluster-build-authorization:0.0.1

# 使用 OpenJDK 21 作为基础镜像
FROM openjdk:21

# 将本地的 JAR 文件添加到容器中并重命名为 authorization.jar
ADD target/authorization-0.0.1.jar authorization.jar

# 暴露容器的端口号
EXPOSE 27001

# 设置容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "/authorization.jar"]

# 构建的镜像说明
LABEL maintainer="fuyy <360687243@qq.com>"
LABEL description="集群-构建-权限"
LABEL version="0.0.1"