DROP TABLE IF EXISTS button;
CREATE TABLE button(
                       `id` bigint AUTO_INCREMENT COMMENT '主键ID' ,
                       `button_name` varchar(200)   COMMENT '名称' ,
                       `button_sign` varchar(200)   COMMENT '标识' ,
                       `on_click` varchar(200)   COMMENT '点击事件' ,
                       `menu_id` bigint   COMMENT '菜单ID' ,
                       `button_order` int   COMMENT '排序;按数字从小到大' ,
                       `remark` varchar(1000)   COMMENT '备注' ,
                       `create_time` datetime   COMMENT '创建时间' ,
                       `creator_id` bigint   COMMENT '创建人ID' ,
                       `creator_name` varchar(50)   COMMENT '创建人名称' ,
                       `update_time` datetime   COMMENT '更新时间' ,
                       `updater_id` bigint   COMMENT '更新人ID' ,
                       `updater_name` varchar(50)   COMMENT '更新人名称' ,
                       `state` tinyint   COMMENT '状态;（0.正常、99.删除）' ,
                       PRIMARY KEY (id)
)  COMMENT = '按钮';

DROP TABLE IF EXISTS menu;
CREATE TABLE menu(
                     `id` bigint NOT NULL  COMMENT '主键ID' ,
                     `menu_name` varchar(200)   COMMENT '名称' ,
                     `menu_url` varchar(500)   COMMENT '路径' ,
                     `menu_icon` varchar(200)   COMMENT '菜单图标' ,
                     `page_name` varchar(200)   COMMENT '页面名称' ,
                     `parent_id` bigint   COMMENT '父级ID' ,
                     `is_turn` tinyint   COMMENT '是否跳转;（0.否、1.是）' ,
                     `is_home` tinyint   COMMENT '是否首页;（0.否、1.是）' ,
                     `menu_order` int   COMMENT '排序;按数字从小到大' ,
                     `remark` varchar(1000)   COMMENT '备注' ,
                     `create_time` datetime   COMMENT '创建时间' ,
                     `creator_id` bigint   COMMENT '创建人ID' ,
                     `creator_name` varchar(50)   COMMENT '创建人名称' ,
                     `update_time` datetime   COMMENT '更新时间' ,
                     `updater_id` bigint   COMMENT '更新人ID' ,
                     `updater_name` varchar(50)   COMMENT '更新人名称' ,
                     `state` tinyint   COMMENT '状态;（0.正常、99.删除）' ,
                     PRIMARY KEY (id)
)  COMMENT = '菜单';

-- 菜单 9条
INSERT INTO `menu` (`id`, `menu_name`, `menu_url`, `menu_icon`, `page_name`, `parent_id`, `is_turn`, `is_home`, `menu_order`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (100,'首页','/home','icon-home','Home',NULL,1,1,100,'首页','2019-11-29 11:27:59',1,'超级管理员','2019-12-16 16:41:56',1,'超级管理员',0),(101,'系统管理',NULL,'icon-desktop',NULL,NULL,0,0,101,'系统管理操作','2019-11-29 11:27:59',1,'超级管理员','2019-12-16 22:38:03',1,'超级管理员',0),(102,'项目群管理',NULL,'icon-project',NULL,NULL,0,0,102,'项目群操作','2019-11-29 11:27:59',1,'超级管理员','2019-12-16 22:38:03',1,'超级管理员',0),(10101,'菜单管理','/menu/manage','icon-menu','MenuManage',101,1,0,10101,'菜单操作','2019-11-29 11:27:59',1,'超级管理员','2019-12-02 21:50:17',1,'超级管理员',0),(10102,'角色管理','/role/manage','icon-audit','RoleManage',101,1,0,10102,'角色操作','2019-11-29 11:27:59',1,'超级管理员','2019-11-29 11:28:07',1,'超级管理员',0),(10103,'用户管理','/manager/manage','icon-user','ManagerManage',101,1,0,10103,'用户操作','2019-11-29 11:27:59',1,'超级管理员','2019-11-29 11:28:07',1,'超级管理员',0),(10104,'参数管理','/parameter/manage','icon-wrench','ParameterManage',101,1,0,10104,'参数设置操作','2019-11-29 11:27:59',1,'超级管理员','2019-12-16 16:41:56',1,'超级管理员',0),(10201,'项目群管理','/group/manage','icon-project','GroupManage',102,1,0,10201,'项目群操作','2019-11-29 11:27:59',1,'超级管理员','2019-12-19 17:09:48',1,'超级管理员',0),(10202,'新增项目群','/group/detail','icon-file-add','GroupDetail',102,1,0,10202,'新增项目群操作','2019-11-29 11:27:59',1,'超级管理员','2019-12-16 16:41:56',1,'超级管理员',0);