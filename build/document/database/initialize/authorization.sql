DROP TABLE IF EXISTS role;
CREATE TABLE role(
                     `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                     `role_name` varchar(200)   COMMENT '名称' ,
                     `remark` varchar(1000)   COMMENT '备注' ,
                     `create_time` datetime   COMMENT '创建时间' ,
                     `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                     `creator_name` varchar(50)   COMMENT '创建人名称' ,
                     `update_time` datetime   COMMENT '更新时间' ,
                     `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                     `updater_name` varchar(50)   COMMENT '更新人名称' ,
                     `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                     PRIMARY KEY (id)
)  COMMENT = '角色';

DROP TABLE IF EXISTS role_button;
CREATE TABLE role_button(
                            `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                            `role_id` bigint unsigned   COMMENT '角色ID' ,
                            `button_id` bigint unsigned   COMMENT '按钮ID' ,
                            `create_time` datetime   COMMENT '创建时间' ,
                            `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                            `creator_name` varchar(50)   COMMENT '创建人名称' ,
                            `update_time` datetime   COMMENT '更新时间' ,
                            `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                            `updater_name` varchar(50)   COMMENT '更新人名称' ,
                            `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                            PRIMARY KEY (id)
)  COMMENT = '角色按钮关系';

DROP TABLE IF EXISTS role_manager;
CREATE TABLE role_manager(
                             `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                             `role_id` bigint unsigned   COMMENT '角色ID' ,
                             `manager_id` bigint unsigned   COMMENT '管理员ID' ,
                             `create_time` datetime   COMMENT '创建时间' ,
                             `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                             `creator_name` varchar(50)   COMMENT '创建人名称' ,
                             `update_time` datetime   COMMENT '更新时间' ,
                             `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                             `updater_name` varchar(50)   COMMENT '更新人名称' ,
                             `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                             PRIMARY KEY (id)
)  COMMENT = '角色管理员关系';

DROP TABLE IF EXISTS role_menu;
CREATE TABLE role_menu(
                          `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                          `role_id` bigint unsigned   COMMENT '角色ID' ,
                          `menu_id` bigint unsigned   COMMENT '菜单ID' ,
                          `create_time` datetime   COMMENT '创建时间' ,
                          `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                          `creator_name` varchar(50)   COMMENT '创建人名称' ,
                          `update_time` datetime   COMMENT '更新时间' ,
                          `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                          `updater_name` varchar(50)   COMMENT '更新人名称' ,
                          `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                          PRIMARY KEY (id)
)  COMMENT = '角色菜单关系';

-- 角色 2条
INSERT INTO `role` (`id`, `role_name`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (1,'超级管理员','超级管理员','2019-12-04 11:09:27',1,'超级管理员','2019-12-09 16:59:37',1,'超级管理员',0),(2,'普通用户','普通用户','2019-12-10 17:28:58',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0);

-- 角色管理员关系 1条
INSERT INTO `role_manager` (`id`, `role_id`, `manager_id`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (1,1,1,'2019-12-16 22:41:25',1,'超级管理员','2019-12-16 22:41:25',1,'超级管理员',0);

-- 角色菜单关系 13条
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (1,1,100,'2019-12-09 16:59:38',1,'超级管理员','2019-12-09 16:59:38',1,'超级管理员',0),(2,1,101,'2019-12-09 16:59:38',1,'超级管理员','2019-12-09 16:59:38',1,'超级管理员',0),(3,1,10101,'2019-12-09 16:59:38',1,'超级管理员','2019-12-09 16:59:38',1,'超级管理员',0),(4,1,10102,'2019-12-09 16:59:38',1,'超级管理员','2019-12-09 16:59:38',1,'超级管理员',0),(5,1,10103,'2019-12-09 16:59:38',1,'超级管理员','2019-12-09 16:59:38',1,'超级管理员',0),(6,1,10104,'2019-12-10 17:29:53',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0),(7,1,102,'2019-12-09 16:59:38',1,'超级管理员','2019-12-09 16:59:38',1,'超级管理员',0),(8,1,10201,'2019-12-09 16:59:39',1,'超级管理员','2019-12-09 16:59:39',1,'超级管理员',0),(9,1,10202,'2019-12-10 17:29:53',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0),(10,2,100,'2019-12-10 17:29:53',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0),(11,2,102,'2019-12-10 17:29:53',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0),(12,2,10201,'2019-12-10 17:29:53',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0),(13,2,10202,'2019-12-10 17:29:53',1,'超级管理员','2019-12-10 17:29:53',1,'超级管理员',0);

