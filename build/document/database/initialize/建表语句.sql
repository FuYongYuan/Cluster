
DROP TABLE IF EXISTS authorization.role;
CREATE TABLE authorization.role(
                                   `id` BIGINT NOT NULL COMMENT '主键ID',
                                   `role_name` VARCHAR(200) COMMENT '名称',
                                   `remark` VARCHAR(1000) COMMENT '备注',
                                   `create_time` DATETIME COMMENT '创建时间',
                                   `creator_id` BIGINT COMMENT '创建人ID',
                                   `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                   `update_time` DATETIME COMMENT '更新时间',
                                   `updater_id` BIGINT COMMENT '更新人ID',
                                   `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                   `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                   `version` BIGINT COMMENT '数据版本',
                                   PRIMARY KEY (`id`)
) COMMENT '角色';


DROP TABLE IF EXISTS authorization.role_button;
CREATE TABLE authorization.role_button(
                                          `id` BIGINT NOT NULL COMMENT '主键ID',
                                          `role_id` BIGINT COMMENT '角色ID',
                                          `button_id` BIGINT COMMENT '按钮ID',
                                          `create_time` DATETIME COMMENT '创建时间',
                                          `creator_id` BIGINT COMMENT '创建人ID',
                                          `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                          `update_time` DATETIME COMMENT '更新时间',
                                          `updater_id` BIGINT COMMENT '更新人ID',
                                          `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                          `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                          `version` BIGINT COMMENT '数据版本',
                                          PRIMARY KEY (`id`)
) COMMENT '角色按钮关系';


DROP TABLE IF EXISTS authorization.role_manager;
CREATE TABLE authorization.role_manager(
                                           `id` BIGINT NOT NULL COMMENT '主键ID',
                                           `role_id` BIGINT COMMENT '角色ID',
                                           `manager_id` BIGINT COMMENT '管理员ID',
                                           `create_time` DATETIME COMMENT '创建时间',
                                           `creator_id` BIGINT COMMENT '创建人ID',
                                           `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                           `update_time` DATETIME COMMENT '更新时间',
                                           `updater_id` BIGINT COMMENT '更新人ID',
                                           `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                           `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                           `version` BIGINT COMMENT '数据版本',
                                           PRIMARY KEY (`id`)
) COMMENT '角色管理员关系';


DROP TABLE IF EXISTS authorization.role_menu;
CREATE TABLE authorization.role_menu(
                                        `id` BIGINT NOT NULL COMMENT '主键ID',
                                        `role_id` BIGINT COMMENT '角色ID',
                                        `menu_id` BIGINT COMMENT '菜单ID',
                                        `create_time` DATETIME COMMENT '创建时间',
                                        `creator_id` BIGINT COMMENT '创建人ID',
                                        `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                        `update_time` DATETIME COMMENT '更新时间',
                                        `updater_id` BIGINT COMMENT '更新人ID',
                                        `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                        `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                        `version` BIGINT COMMENT '数据版本',
                                        PRIMARY KEY (`id`)
) COMMENT '角色菜单关系';


DROP TABLE IF EXISTS builder.commonly_version;
CREATE TABLE builder.commonly_version(
                                         `id` BIGINT NOT NULL COMMENT '主键ID',
                                         `manager_id` BIGINT COMMENT '管理员ID',
                                         `jdk_version` VARCHAR(100) COMMENT 'JDK版本',
                                         `spring_cloud_version` VARCHAR(100) COMMENT 'SpringCloudVersion',
                                         `spring_cloud_alibaba_version` VARCHAR(100) COMMENT 'SpringCloudAlibabaVersion',
                                         `spring_boot_version` VARCHAR(100) COMMENT 'SpringBootVersion',
                                         `open_api_version` VARCHAR(100) COMMENT 'API文档组件',
                                         `lombok_version` VARCHAR(100) COMMENT '简化对象lombok',
                                         `druid_version` VARCHAR(100) COMMENT '数据访问监控Druid',
                                         `mybatis_version` VARCHAR(100) COMMENT '数据库框架MyBatis版本',
                                         `create_time` DATETIME COMMENT '创建时间',
                                         `creator_id` BIGINT COMMENT '创建人ID',
                                         `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                         `update_time` DATETIME COMMENT '更新时间',
                                         `updater_id` BIGINT COMMENT '更新人ID',
                                         `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                         `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                         `version` BIGINT COMMENT '数据版本',
                                         PRIMARY KEY (`id`)
) COMMENT '常用版本';


DROP TABLE IF EXISTS builder.data_base_file;
CREATE TABLE builder.data_base_file(
                                       `id` BIGINT NOT NULL COMMENT '主键ID',
                                       `project_group_id` BIGINT COMMENT '项目群ID',
                                       `file_url` VARCHAR(200) COMMENT '文件地址',
                                       `create_time` DATETIME COMMENT '创建时间',
                                       `creator_id` BIGINT COMMENT '创建人ID',
                                       `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                       `update_time` DATETIME COMMENT '更新时间',
                                       `updater_id` BIGINT COMMENT '更新人ID',
                                       `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                       `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                       `version` BIGINT COMMENT '数据版本',
                                       PRIMARY KEY (`id`)
) COMMENT '数据库设计文件';


DROP TABLE IF EXISTS builder.project;
CREATE TABLE builder.project(
                                `id` BIGINT NOT NULL COMMENT '主键ID',
                                `project_group_id` BIGINT COMMENT '项目群ID',
                                `project_name` VARCHAR(200) COMMENT '项目名称',
                                `artifact_id` VARCHAR(100) COMMENT '工件ID',
                                `package_name` VARCHAR(100) COMMENT '项目包目录',
                                `project_version` VARCHAR(100) COMMENT '项目版本',
                                `project_description` VARCHAR(4000) COMMENT '项目的描述',
                                `project_url` VARCHAR(100) COMMENT '项目访问路径',
                                `project_port` VARCHAR(5) COMMENT '项目访问端口',
                                `is_service_interface` TINYINT COMMENT '是否Service创建接口;（0.否、1.是）',
                                `is_delete_bo` TINYINT COMMENT '是否去除BO层;（0.否、1.是）',
                                `is_basis_method` TINYINT COMMENT '是否生成基础方法;（0.否、1.是）',
                                `is_redis` TINYINT COMMENT '是否使用Redis缓存;（0.否、1.是）',
                                `is_minio` TINYINT COMMENT '是否使用MinIO文件服务器;（0.否、1.是）',
                                `is_mail` TINYINT COMMENT '是否使用Mail能力;（0.否、1.是）',
                                `is_dispose` TINYINT COMMENT '是否使用内容处理能力;（0.否、1.是）',
                                `is_encrypt` TINYINT COMMENT '是否使用加密能力;（0.否、1.是）',
                                `is_excel` TINYINT COMMENT '是否使用Excel能力;（0.否、1.是）',
                                `druid_url` VARCHAR(200) COMMENT 'Druid访问路径',
                                `druid_account` VARCHAR(50) COMMENT 'Druid登录账号',
                                `druid_password` VARCHAR(50) COMMENT 'Druid登录密码',
                                `is_delete_not_pk` TINYINT COMMENT '是否去除无主键的表;（0.否、1.是）',
                                `is_order_key` TINYINT COMMENT '是否吧主键生成时放第一位;（0.否、1.是）',
                                `data_base_framework` TINYINT COMMENT '数据层框架类型;（0.不使用数据源、1.JPA、2.Mybatis）',
                                `data_base_format` TINYINT COMMENT '数据库格式设定;（0.没有格式转换、1.X_X_Xxxx => XXXXxxx）',
                                `create_time` DATETIME COMMENT '创建时间',
                                `creator_id` BIGINT COMMENT '创建人ID',
                                `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                `update_time` DATETIME COMMENT '更新时间',
                                `updater_id` BIGINT COMMENT '更新人ID',
                                `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                `version` BIGINT COMMENT '数据版本',
                                PRIMARY KEY (`id`)
) COMMENT '项目';


DROP TABLE IF EXISTS builder.project_column;
CREATE TABLE builder.project_column(
                                       `id` BIGINT NOT NULL COMMENT '主键ID',
                                       `project_table_id` BIGINT COMMENT '项目表ID',
                                       `table_schema` VARCHAR(200) COMMENT '所属的库',
                                       `table_name` VARCHAR(200) COMMENT '表名称',
                                       `column_name` VARCHAR(200) COMMENT '字段名',
                                       `is_nullable` VARCHAR(5) COMMENT '是否可以为空',
                                       `data_type` VARCHAR(20) COMMENT '数据类型;不包括长度',
                                       `character_maximum_length` INT COMMENT '数据长度varchar',
                                       `numeric_precision` INT COMMENT '数字类型的整型部分长度',
                                       `numeric_scale` INT COMMENT '数字类型的小数部分长度',
                                       `column_type` VARCHAR(200) COMMENT '字段类型',
                                       `column_key` VARCHAR(5) COMMENT 'PRI为主键，MUL为外键',
                                       `extra` VARCHAR(200) COMMENT '自增标识',
                                       `column_comment` VARCHAR(1000) COMMENT '字段注释',
                                       `create_time` DATETIME COMMENT '创建时间',
                                       `creator_id` BIGINT COMMENT '创建人ID',
                                       `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                       `update_time` DATETIME COMMENT '更新时间',
                                       `updater_id` BIGINT COMMENT '更新人ID',
                                       `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                       `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                       `version` BIGINT COMMENT '数据版本',
                                       PRIMARY KEY (`id`)
) COMMENT '项目表字段';


DROP TABLE IF EXISTS builder.project_group;
CREATE TABLE builder.project_group(
                                      `id` BIGINT NOT NULL COMMENT '主键ID',
                                      `manager_id` BIGINT COMMENT '管理员ID',
                                      `project_group_name` VARCHAR(200) COMMENT '项目群名称',
                                      `group_id` VARCHAR(100) COMMENT '组ID',
                                      `artifact_id` VARCHAR(100) COMMENT '工件ID',
                                      `group_version` VARCHAR(100) COMMENT '项目群版本号',
                                      `jdk_version` VARCHAR(100) COMMENT 'JDK版本',
                                      `spring_cloud_version` VARCHAR(100) COMMENT 'SpringCloudVersion',
                                      `spring_cloud_alibaba_version` VARCHAR(100) COMMENT 'SpringCloudAlibabaVersion',
                                      `spring_boot_version` VARCHAR(100) COMMENT 'SpringBootVersion',
                                      `open_api_version` VARCHAR(100) COMMENT 'API文档组件',
                                      `lombok_version` VARCHAR(100) COMMENT '简化对象lombok',
                                      `druid_version` VARCHAR(100) COMMENT '数据访问监控Druid',
                                      `mybatis_version` VARCHAR(100) COMMENT '数据库框架MyBatis版本',
                                      `remark` VARCHAR(1000) COMMENT '备注',
                                      `create_time` DATETIME COMMENT '创建时间',
                                      `creator_id` BIGINT COMMENT '创建人ID',
                                      `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                      `update_time` DATETIME COMMENT '更新时间',
                                      `updater_id` BIGINT COMMENT '更新人ID',
                                      `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                      `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                      `version` BIGINT COMMENT '数据版本',
                                      PRIMARY KEY (`id`)
) COMMENT '项目群';


DROP TABLE IF EXISTS builder.project_project_table;
CREATE TABLE builder.project_project_table(
                                              `id` BIGINT NOT NULL COMMENT '主键ID',
                                              `project_id` BIGINT COMMENT '项目ID',
                                              `project_table_id` BIGINT COMMENT '项目表ID',
                                              `create_time` DATETIME COMMENT '创建时间',
                                              `creator_id` BIGINT COMMENT '创建人ID',
                                              `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                              `update_time` DATETIME COMMENT '更新时间',
                                              `updater_id` BIGINT COMMENT '更新人ID',
                                              `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                              `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                              `version` BIGINT COMMENT '数据版本',
                                              PRIMARY KEY (`id`)
) COMMENT '项目项目表关系';


DROP TABLE IF EXISTS builder.project_table;
CREATE TABLE builder.project_table(
                                      `id` BIGINT NOT NULL COMMENT '主键ID',
                                      `project_group_id` BIGINT COMMENT '项目群ID',
                                      `data_base_file_id` BIGINT COMMENT '数据库设计文件ID',
                                      `table_schema` VARCHAR(200) COMMENT '所属的库',
                                      `table_name` VARCHAR(200) COMMENT '表名称',
                                      `table_comment` VARCHAR(1000) COMMENT '表注释',
                                      `create_time` DATETIME COMMENT '创建时间',
                                      `creator_id` BIGINT COMMENT '创建人ID',
                                      `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                      `update_time` DATETIME COMMENT '更新时间',
                                      `updater_id` BIGINT COMMENT '更新人ID',
                                      `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                      `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                      `version` BIGINT COMMENT '数据版本',
                                      PRIMARY KEY (`id`)
) COMMENT '项目表';


DROP TABLE IF EXISTS capability.button;
CREATE TABLE capability.button(
                                  `id` BIGINT NOT NULL COMMENT '主键ID',
                                  `button_name` VARCHAR(200) COMMENT '名称',
                                  `button_sign` VARCHAR(200) COMMENT '标识',
                                  `on_click` VARCHAR(200) COMMENT '点击事件',
                                  `menu_id` BIGINT COMMENT '菜单ID',
                                  `button_order` INT COMMENT '排序;按数字从小到大',
                                  `remark` VARCHAR(1000) COMMENT '备注',
                                  `create_time` DATETIME COMMENT '创建时间',
                                  `creator_id` BIGINT COMMENT '创建人ID',
                                  `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                  `update_time` DATETIME COMMENT '更新时间',
                                  `updater_id` BIGINT COMMENT '更新人ID',
                                  `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                  `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                  `version` BIGINT COMMENT '数据版本',
                                  PRIMARY KEY (`id`)
) COMMENT '按钮';


DROP TABLE IF EXISTS capability.menu;
CREATE TABLE capability.menu(
                                `id` BIGINT NOT NULL COMMENT '主键ID',
                                `menu_name` VARCHAR(200) COMMENT '名称',
                                `menu_url` VARCHAR(500) COMMENT '路径',
                                `menu_icon` VARCHAR(200) COMMENT '菜单图标',
                                `page_name` VARCHAR(200) COMMENT '页面名称',
                                `parent_id` BIGINT COMMENT '父级ID',
                                `is_turn` TINYINT COMMENT '是否跳转;（0.否、1.是）',
                                `is_home` TINYINT COMMENT '是否首页;（0.否、1.是）',
                                `menu_order` INT COMMENT '排序;按数字从小到大',
                                `remark` VARCHAR(1000) COMMENT '备注',
                                `create_time` DATETIME COMMENT '创建时间',
                                `creator_id` BIGINT COMMENT '创建人ID',
                                `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                `update_time` DATETIME COMMENT '更新时间',
                                `updater_id` BIGINT COMMENT '更新人ID',
                                `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                `version` BIGINT COMMENT '数据版本',
                                PRIMARY KEY (`id`)
) COMMENT '菜单';


DROP TABLE IF EXISTS dictionary.parameter;
CREATE TABLE dictionary.parameter(
                                     `id` BIGINT NOT NULL COMMENT '主键ID',
                                     `parameter_code` VARCHAR(200) COMMENT '参数代码',
                                     `parameter_name` VARCHAR(200) COMMENT '参数名称',
                                     `parameter_value` TEXT COMMENT '参数值',
                                     `parameter_explain` VARCHAR(4000) COMMENT '参数说明',
                                     `create_time` DATETIME COMMENT '创建时间',
                                     `creator_id` BIGINT COMMENT '创建人ID',
                                     `creator_name` VARCHAR(50) COMMENT '创建人名称',
                                     `update_time` DATETIME COMMENT '更新时间',
                                     `updater_id` BIGINT COMMENT '更新人ID',
                                     `updater_name` VARCHAR(50) COMMENT '更新人名称',
                                     `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                                     `version` BIGINT COMMENT '数据版本',
                                     PRIMARY KEY (`id`)
) COMMENT '参数';


DROP TABLE IF EXISTS member.manager;
CREATE TABLE member.manager(
                               `id` BIGINT NOT NULL COMMENT '主键ID',
                               `avatar` VARCHAR(1000) COMMENT '头像',
                               `manager_name` VARCHAR(50) COMMENT '姓名',
                               `age` TINYINT COMMENT '年龄',
                               `sex` TINYINT COMMENT '性别;（0.保密、1.男、2女）',
                               `mobile` VARCHAR(20) COMMENT '手机号',
                               `mail` VARCHAR(200) COMMENT '邮箱',
                               `account` VARCHAR(50) COMMENT '账号',
                               `login_password` VARCHAR(32) COMMENT '登陆密码',
                               `last_attempt_login_request_ip` VARCHAR(100) COMMENT '最后尝试请求IP',
                               `last_attempt_login_time` DATETIME COMMENT '最后尝试登陆时间',
                               `attempt_login_number` TINYINT COMMENT '尝试登陆次数',
                               `request_ip` VARCHAR(100) COMMENT '常用请求IP',
                               `current_request_ip` VARCHAR(100) COMMENT '当前请求IP',
                               `remark` VARCHAR(1000) COMMENT '备注',
                               `create_time` DATETIME COMMENT '创建时间',
                               `creator_id` BIGINT COMMENT '创建人ID',
                               `creator_name` VARCHAR(50) COMMENT '创建人名称',
                               `update_time` DATETIME COMMENT '更新时间',
                               `updater_id` BIGINT COMMENT '更新人ID',
                               `updater_name` VARCHAR(50) COMMENT '更新人名称',
                               `state` TINYINT COMMENT '状态;（0.正常、1.冻结、99.删除）',
                               `version` BIGINT COMMENT '数据版本',
                               PRIMARY KEY (`id`)
) COMMENT '管理员';


DROP TABLE IF EXISTS message.notice;
CREATE TABLE message.notice(
                               `id` BIGINT NOT NULL COMMENT '主键ID',
                               `notice_title` VARCHAR(200) COMMENT '公告标题',
                               `notice_content` TEXT COMMENT '公告内容',
                               `notice_author` VARCHAR(200) COMMENT '公告作者',
                               `notice_order` INT COMMENT '公告排序',
                               `create_time` DATETIME COMMENT '创建时间',
                               `creator_id` BIGINT COMMENT '创建人ID',
                               `creator_name` VARCHAR(50) COMMENT '创建人名称',
                               `update_time` DATETIME COMMENT '更新时间',
                               `updater_id` BIGINT COMMENT '更新人ID',
                               `updater_name` VARCHAR(50) COMMENT '更新人名称',
                               `state` TINYINT COMMENT '状态;（0.正常、99.删除）',
                               `version` BIGINT COMMENT '数据版本',
                               PRIMARY KEY (`id`)
) COMMENT '公告';




INSERT INTO authorization.role (`id`, `role_name`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6508370869929984,'超级管理员','超级管理员','2019-12-04 11:09:27',6508370886681600,'超级管理员','2019-12-09 16:59:37',6508370886681600,'超级管理员',0,0),
       (6514166638888960,'普通用户','普通用户','2019-12-10 17:28:58',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0);


INSERT INTO authorization.role_manager (`id`, `role_id`, `manager_id`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6514166622111744,6508370869929984,6508370886681600,'2019-12-16 22:41:25',6508370886681600,'超级管理员','2019-12-16 22:41:25',6508370886681600,'超级管理员',0,0);

INSERT INTO authorization.role_menu (`id`, `role_id`, `menu_id`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6508370886707201,6508370869929984,6508370886732800,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),
       (6508370886707202,6508370869929984,6508370886732801,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),
       (6508370886707203,6508370869929984,6508370886732803,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),
       (6508370886707204,6508370869929984,6508370886732804,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),
       (6508370886707205,6508370869929984,6508370886732805,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),
       (6508370886707206,6508370869929984,6508370886732806,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),
       (6508370886707207,6508370869929984,6508370886732802,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),
       (6508370886707208,6508370869929984,6508370886732807,'2019-12-09 16:59:39',6508370886681600,'超级管理员','2019-12-09 16:59:39',6508370886681600,'超级管理员',0,0),
       (6508370886707209,6508370869929984,6508370886732808,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),
       (6508370886707210,6514166638888960,6508370886732800,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),
       (6508370886707211,6514166638888960,6508370886732802,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),
       (6508370886707212,6514166638888960,6508370886732807,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),
       (6508370886707213,6514166638888960,6508370886732808,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0);

INSERT INTO capability.menu (`id`, `menu_name`, `menu_url`, `menu_icon`, `page_name`, `parent_id`, `is_turn`, `is_home`, `menu_order`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6508370886732800,'首页','/home','icon-home','Home',NULL,1,1,100,'首页','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 16:41:56',6508370886681600,'超级管理员',0,0),
       (6508370886732801,'系统管理',NULL,'icon-desktop',NULL,NULL,0,0,101,'系统管理操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 22:38:03',6508370886681600,'超级管理员',0,0),
       (6508370886732802,'项目群管理',NULL,'icon-project',NULL,NULL,0,0,102,'项目群操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 22:38:03',6508370886681600,'超级管理员',0,0),
       (6508370886732803,'菜单管理','/menu/manage','icon-menu','MenuManage',6508370886732801,1,0,10101,'菜单操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-02 21:50:17',6508370886681600,'超级管理员',0,0),
       (6508370886732804,'角色管理','/role/manage','icon-audit','RoleManage',6508370886732801,1,0,10102,'角色操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-11-29 11:28:07',6508370886681600,'超级管理员',0,0),
       (6508370886732805,'用户管理','/manager/manage','icon-user','ManagerManage',6508370886732801,1,0,10103,'用户操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-11-29 11:28:07',6508370886681600,'超级管理员',0,0),
       (6508370886732806,'参数管理','/parameter/manage','icon-wrench','ParameterManage',6508370886732801,1,0,10104,'参数设置操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 16:41:56',6508370886681600,'超级管理员',0,0),
       (6508370886732807,'项目群管理','/group/manage','icon-project','GroupManage',6508370886732802,1,0,10201,'项目群操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-19 17:09:48',6508370886681600,'超级管理员',0,0),
       (6508370886732808,'新增项目群','/group/detail','icon-file-add','GroupDetail',6508370886732802,1,0,10202,'新增项目群操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 16:41:56',6508370886681600,'超级管理员',0,0);

INSERT INTO dictionary.parameter (`id`, `parameter_code`, `parameter_name`, `parameter_value`, `parameter_explain`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6508370886758400,'CreateProjectGroupMaxNumber','创建项目群最大数','3','用于限制个人创建项目群最大数量','2019-12-30 09:20:07',6508370886681600,'超级管理员','2020-01-03 23:25:02',6508370886681600,'超级管理员',0,0);


INSERT INTO member.manager (`id`, `avatar`, `manager_name`, `age`, `sex`, `mobile`, `mail`, `account`, `login_password`, `last_attempt_login_request_ip`, `last_attempt_login_time`, `attempt_login_number`, `request_ip`, `current_request_ip`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6508370886681600,'a070f2e333544a61b694cf33a646c698.jpeg','超级管理员',77,0,'13581876056','fuyongyuan277@sina.cn','admin','c4ca4238a0b923820dcc509a6f75849b','172.21.138.159','2026-01-04 10:31:23',0,NULL,'172.21.138.159','超级管理员','2019-10-09 17:23:08',6508370886681600,'超级管理员','2026-01-04 10:31:24',6508370886681600,'超级管理员',0,0);


INSERT INTO message.notice (`id`, `notice_title`, `notice_content`, `notice_author`, `notice_order`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`)
VALUES (6508370886809600,NULL,NULL,'超级管理员',1,'2019-10-09 17:23:08',6508370886681600,'超级管理员','2020-04-30 11:10:06',6508370886681600,'超级管理员',0,0);


