DROP TABLE IF EXISTS parameter;
CREATE TABLE parameter(
                          `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                          `parameter_code` varchar(200)   COMMENT '参数代码' ,
                          `parameter_name` varchar(200)   COMMENT '参数名称' ,
                          `parameter_value` text   COMMENT '参数值' ,
                          `parameter_explain` varchar(4000)   COMMENT '参数说明' ,
                          `create_time` datetime   COMMENT '创建时间' ,
                          `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                          `creator_name` varchar(50)   COMMENT '创建人名称' ,
                          `update_time` datetime   COMMENT '更新时间' ,
                          `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                          `updater_name` varchar(50)   COMMENT '更新人名称' ,
                          `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                          PRIMARY KEY (id)
)  COMMENT = '参数';

-- 参数 1条
INSERT INTO `parameter` (`id`, `parameter_code`, `parameter_name`, `parameter_value`, `parameter_explain`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (1,'CreateProjectGroupMaxNumber','创建项目群最大数','3','用于限制个人创建项目群最大数量','2019-12-30 09:20:07',1,'超级管理员','2020-01-03 23:25:02',1,'超级管理员',0);