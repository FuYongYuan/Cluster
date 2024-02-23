use builder;

create table builder.project_column
(
    id                       bigint unsigned auto_increment comment '主键ID'
        primary key,
    project_table_id         bigint unsigned  null comment '项目表ID',
    table_schema             varchar(200)     null comment '所属的库',
    table_name               varchar(200)     null comment '表名称',
    column_name              varchar(200)     null comment '字段名',
    is_nullable              varchar(5)       null comment '是否可以为空',
    data_type                varchar(20)      null comment '数据类型(不包括长度)',
    character_maximum_length int              null comment '数据长度varchar',
    numeric_precision        int              null comment '数字类型的整型部分长度',
    numeric_scale            int              null comment '数字类型的小数部分长度',
    column_type              varchar(200)     null comment '字段类型',
    column_key               varchar(5)       null comment 'PRI为主键，MUL为外键',
    extra                    varchar(200)     null comment '自增标识',
    column_comment           varchar(1000)    null comment '字段注释',
    create_time              datetime         null comment '创建时间',
    creator_id               bigint unsigned  null comment '创建人ID',
    creator_name             varchar(50)      null comment '创建人名称',
    update_time              datetime         null comment '修改时间',
    updater_id               bigint unsigned  null comment '修改人ID',
    updater_name             varchar(50)      null comment '修改人名称',
    state                    tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '项目表字段';

