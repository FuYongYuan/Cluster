use authorization;

create table authorization.role_manager
(
    id           bigint unsigned auto_increment comment '主键ID'
        primary key,
    role_id      bigint unsigned  null comment '角色ID',
    manager_id   bigint unsigned  null comment '管理员ID',
    create_time  datetime         null comment '创建时间',
    creator_id   bigint unsigned  null comment '创建人ID',
    creator_name varchar(50)      null comment '创建人名称',
    update_time  datetime         null comment '修改时间',
    updater_id   bigint unsigned  null comment '修改人ID',
    updater_name varchar(50)      null comment '修改人名称',
    state        tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '角色管理员关系';

