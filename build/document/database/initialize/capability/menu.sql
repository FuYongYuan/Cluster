use capability;

create table capability.menu
(
    id           bigint unsigned  not null comment '主键ID'
        primary key,
    menu_name    varchar(200)     null comment '名称',
    menu_url     varchar(500)     null comment '路径',
    menu_icon    varchar(200)     null comment '菜单图标',
    page_name    varchar(200)     null comment '页面名称',
    parent_id    bigint unsigned  null comment '父级ID',
    is_turn      tinyint unsigned null comment '是否跳转(0.否 1.是)',
    is_home      tinyint unsigned null comment '是否首页(0.否 1.是)',
    menu_order   int              null comment '排序(按数字从小到大)',
    remark       varchar(1000)    null comment '备注',
    create_time  datetime         null comment '创建时间',
    creator_id   bigint unsigned  null comment '创建人ID',
    creator_name varchar(50)      null comment '创建人名称',
    update_time  datetime         null comment '修改时间',
    updater_id   bigint unsigned  null comment '修改人ID',
    updater_name varchar(50)      null comment '修改人名称',
    state        tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '菜单';

