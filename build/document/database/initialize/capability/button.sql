use capability;

create table capability.button
(
    id           bigint unsigned auto_increment comment '主键ID'
        primary key,
    button_name  varchar(200)     null comment '名称',
    button_sign  varchar(200)     null comment '标识',
    on_click     varchar(200)     null comment '点击事件',
    menu_id      bigint unsigned  null comment '菜单ID',
    button_order int              null comment '排序(按数字从小到大)',
    remark       varchar(1000)    null comment '备注',
    create_time  datetime         null comment '创建时间',
    creator_id   bigint unsigned  null comment '创建人ID',
    creator_name varchar(50)      null comment '创建人名称',
    update_time  datetime         null comment '修改时间',
    updater_id   bigint unsigned  null comment '修改人ID',
    updater_name varchar(50)      null comment '修改人名称',
    state        tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '按钮';

