use dictionary;

create table dictionary.parameter
(
    id                bigint unsigned auto_increment comment '主键ID'
        primary key,
    parameter_code    varchar(200)     null comment '参数代码',
    parameter_name    varchar(200)     null comment '参数名称',
    parameter_value   text             null comment '参数值',
    parameter_explain varchar(4000)    null comment '参数说明',
    create_time       datetime         null comment '创建时间',
    creator_id        bigint unsigned  null comment '创建人ID',
    creator_name      varchar(50)      null comment '创建人名称',
    update_time       datetime         null comment '修改时间',
    updater_id        bigint unsigned  null comment '修改人ID',
    updater_name      varchar(50)      null comment '修改人名称',
    state             tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '参数';

