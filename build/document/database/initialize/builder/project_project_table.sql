use builder;

create table builder.project_project_table
(
    id               bigint unsigned auto_increment comment '主键ID'
        primary key,
    project_id       bigint unsigned  null comment '项目ID',
    project_table_id bigint unsigned  null comment '项目表ID',
    create_time      datetime         null comment '创建时间',
    creator_id       bigint unsigned  null comment '创建人ID',
    creator_name     varchar(50)      null comment '创建人名称',
    update_time      datetime         null comment '修改时间',
    updater_id       bigint unsigned  null comment '修改人ID',
    updater_name     varchar(50)      null comment '修改人名称',
    state            tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '项目项目表关系';

