DROP TABLE IF EXISTS notice;
CREATE TABLE notice(
                       `id` bigint AUTO_INCREMENT COMMENT '主键ID' ,
                       `notice_title` varchar(200)   COMMENT '公告标题' ,
                       `notice_content` text   COMMENT '公告内容' ,
                       `notice_author` varchar(200)   COMMENT '公告作者' ,
                       `notice_order` int   COMMENT '公告排序' ,
                       `create_time` datetime   COMMENT '创建时间' ,
                       `creator_id` bigint   COMMENT '创建人ID' ,
                       `creator_name` varchar(50)   COMMENT '创建人名称' ,
                       `update_time` datetime   COMMENT '更新时间' ,
                       `updater_id` bigint   COMMENT '更新人ID' ,
                       `updater_name` varchar(50)   COMMENT '更新人名称' ,
                       `state` tinyint   COMMENT '状态;（0.正常、99.删除）' ,
                       PRIMARY KEY (id)
)  COMMENT = '公告';

-- 公告 1条
INSERT INTO `notice` (`id`, `notice_title`, `notice_content`, `notice_author`, `notice_order`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (1,NULL,NULL,'超级管理员',1,'2019-10-09 17:23:08',1,'超级管理员','2020-04-30 11:10:06',1,'超级管理员',0);
