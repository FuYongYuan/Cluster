use member;

create table member.manager
(
    id                            bigint unsigned auto_increment comment '主键ID'
        primary key,
    head_img_url                  varchar(1000)    null comment '头像',
    manager_name                  varchar(50)      null comment '姓名',
    age                           tinyint unsigned null comment '年龄',
    sex                           tinyint unsigned null comment '性别(0.保密 1.男 2女)',
    mobile                        varchar(20)      null comment '手机号',
    mail                          varchar(200)     null comment '邮箱',
    account                       varchar(50)      null comment '账号',
    login_password                varchar(32)      null comment '登陆密码',
    last_attempt_login_request_ip varchar(100)     null comment '最后尝试请求IP',
    last_attempt_login_time       datetime         null comment '最后尝试登陆时间',
    attempt_login_number          tinyint unsigned null comment '尝试登陆次数',
    request_ip                    varchar(100)     null comment '常用请求IP',
    current_request_ip            varchar(100)     null comment '当前请求IP',
    remark                        varchar(1000)    null comment '备注',
    create_time                   datetime         null comment '创建时间',
    creator_id                    bigint unsigned  null comment '创建人ID',
    creator_name                  varchar(50)      null comment '创建人名称',
    update_time                   datetime         null comment '修改时间',
    updater_id                    bigint unsigned  null comment '修改人ID',
    updater_name                  varchar(50)      null comment '修改人名称',
    state                         tinyint unsigned null comment '状态(0.正常 99.删除)'
)
    comment '管理员';

