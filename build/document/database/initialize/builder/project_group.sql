use builder;

create table builder.project_group
(
    id                           bigint unsigned auto_increment comment '主键ID'
        primary key,
    manager_id                   bigint unsigned  null comment '管理员ID',
    project_group_name           varchar(200)     null comment '项目群名称',
    group_id                     varchar(100)     null comment '组ID',
    artifact_id                  varchar(100)     null comment '工件ID',
    version                      varchar(100)     null comment '项目群版本号',
    jdk_version                  varchar(100)     null comment 'JDK版本',
    spring_cloud_version         varchar(100)     null comment 'SpringCloudVersion',
    spring_cloud_alibaba_version varchar(100)     null comment 'SpringCloudAlibabaVersion',
    spring_boot_version          varchar(100)     null comment 'SpringBootVersion',
    api_version                  varchar(100)     null comment 'API文档组件',
    lombok_version               varchar(100)     null comment '简化对象lombok',
    druid_version                varchar(100)     null comment '数据访问监控Druid',
    mybatis_version              varchar(100)     null comment '数据库框架MyBatis版本',
    remark                       varchar(1000)    null comment '备注',
    create_time                  datetime         null comment '创建时间',
    creator_id                   bigint unsigned  null comment '创建人ID',
    creator_name                 varchar(50)      null comment '创建人名称',
    update_time                  datetime         null comment '修改时间',
    updater_id                   bigint unsigned  null comment '修改人ID',
    updater_name                 varchar(50)      null comment '修改人名称',
    state                        tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '项目群';

