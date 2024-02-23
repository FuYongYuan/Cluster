use message;

create table message.notice
(
    id             bigint unsigned auto_increment comment '主键ID'
        primary key,
    notice_title   varchar(200)     null comment '公告标题',
    notice_content text             null comment '公告内容',
    notice_author  varchar(200)     null comment '公告作者',
    notice_order   int              null comment '公告排序',
    create_time    datetime         null comment '创建时间',
    creator_id     bigint unsigned  null comment '创建人ID',
    creator_name   varchar(50)      null comment '创建人名称',
    update_time    datetime         null comment '修改时间',
    updater_id     bigint unsigned  null comment '修改人ID',
    updater_name   varchar(50)      null comment '修改人名称',
    state          tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '公告';

