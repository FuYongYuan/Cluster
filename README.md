# FYY 项目群说明文档

## 目录结构说明

### build - 项目构建相关

- **back** - 后端模块
    - **ability** - 能力模块
        - **common** - 公共能力
        - **database** - 数据库能力 (MySQL)
        - **elasticsearch** - Elasticsearch 能力
        - **gateway** - 网关能力
        - **jwt** - JWT 鉴权能力
        - **mail** - 邮件能力
        - **minio** - 文件存储能力
        - **mongodb** - MongoDB 非关系型数据库能力
        - **redis** - Redis 能力
        - **rocketmq** - RocketMQ 消息队列能力
<br>
<br>
    - **base** - 基础模块
        - **dispose** - 处理模块
        - **encrypt** - 加密模块
        - **excel** - excel处理模块
        - **schedule** - 单体调度模块
<br>
<br>
    - **bean** - 实体模块
        - **authorization-bean** - 授权实体模块
        - **builder-bean** - 构建实体模块
        - **capability-bean** - 能力实体模块
        - **common-bean** - 公共实体模块
        - **dictionary-bean** - 字典实体模块
        - **elasticsearch-bean** - Elasticsearch 实体模块
        - **member-bean** - 用户实体模块
        - **message-bean** - 消息实体模块
        - **mongodb-bean** - MongoDB 模块
        - **rocketmq-bean** - RocketMQ 模块
<br>
<br>
    - **http-test** - Http 测试模块
<br>
<br>
    - **service** - 服务模块
        - **authorization** - 授权服务模块
        - **builder** - 构建服务模块
        - **capability** - 能力服务模块
        - **data** - 数据服务模块
        - **dictionary** - 字典服务模块
        - **job** - 调度服务模块
        - **member** - 用户服务模块
        - **message** - 消息服务模块
<br>
<br>
- **document** - 文档
    - **database** - 数据库说明文档
        - **initialize** - 初始化脚本
        - **structure** - 数据库结构 - 使用 PDManer 打开json
<br>
<br>
    - **docker** - Docker 部署说明文档
        - **elk** - ELK 日志管理部署 - 含 Elasticsearch【日志存储】、Kibana【日志查询】、Logstash【日志存储】、Filebeat【日志采集】
        - **minio** - MinIO 文件存储部署
        - **mongodb** - MongoDB 部署
        - **mysql** - MySQL 数据库部署
        - **nacos** - Nacos 配置管理部署
        - **redis** - Redis 缓存部署
        - **rocketmq** - RocketMQ 消息队列部署 - 含 NameServer【消息队列】、Broker【消息队列】、Proxy【消息队列】
        - **seata** - Seata 分布式事务管理器部署
        - **sentinel-dashboard** - Sentinel 熔断器部署
        - **skywalking** - Skywalking 链路追踪部署 - 含 Skywalking-oap【链路追踪-服务】、Skywalking-ui【链路追踪-UI】
        - **xxl-job** - XXL-JOB 任务调度部署
<br>
<br>
- **front** - 前端模块
    - **public** - 公共模块 - 包含图标、字体、图片等
    - **src** - 源代码
        - **antd** - antd 组件
        - **apis** - 接口
            - **commons** - 公共模块接口
            - **xxx** - xxx模块接口
        - **assets** - 资源
            - **css** - css
            - **iconfont** - 字体图标
        - **components** - 组件
            - **error** - 错误处理
            - **layout** - 布局
            - **logo** - logo
        - **hooks** - 自定义 Hooks
        - **router** - 路由
        - **utils** - 工具
            - **axios** - axios请求封装
            - **validate** - 验证封装
        - **views** - 页面
          - **xxx** - xxx模块页面
---
### build - 项目构建方式
1. 【组件】前往 ./document/docker 目录，根据【docker全镜像拉取语句备份.txt】挨个启动安装和组件，相关说明文档已放在各种的目录下【docker启动语句备份.txt】中，除skywalking需要做额外操作来保证运行以外其他启动后根据自身需要修改配置即可；
2. 【前端】前往 ./front/src 目录，执行 npm install 安装依赖后即可启动；
3. 【后端】前往 idea 打开后，maven读取正常，应该在【服务】这个插件下可以看到所有可启动服务，添加好skywalking内容后即可启动；
>   -javaagent:/Users/fyy/work/tool/skywalking/skywalking-agent/skywalking-agent.jar \
>   -Dskywalking.agent.service_name=build::gateway \
>   -Dskywalking.collector.backend_service=127.0.0.1:11800