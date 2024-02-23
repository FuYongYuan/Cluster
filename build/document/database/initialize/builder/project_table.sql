use builder;

create table builder.project_table
(
    id                bigint unsigned auto_increment comment '主键ID'
        primary key,
    project_group_id  bigint unsigned  null comment '项目群ID',
    data_base_file_id bigint unsigned  null comment '数据库设计文件ID',
    table_schema      varchar(200)     null comment '所属的库',
    table_name        varchar(200)     null comment '表名称',
    table_comment     varchar(1000)    null comment '表注释',
    create_time       datetime         null comment '创建时间',
    creator_id        bigint unsigned  null comment '创建人ID',
    creator_name      varchar(50)      null comment '创建人名称',
    update_time       datetime         null comment '修改时间',
    updater_id        bigint unsigned  null comment '修改人ID',
    updater_name      varchar(50)      null comment '修改人名称',
    state             tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '项目表';

