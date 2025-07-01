DROP TABLE IF EXISTS manager;
CREATE TABLE manager(
                        `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                        `head_img_url` varchar(1000)   COMMENT '头像' ,
                        `manager_name` varchar(50)   COMMENT '姓名' ,
                        `age` tinyint unsigned   COMMENT '年龄' ,
                        `sex` tinyint unsigned   COMMENT '性别;（0.保密、1.男、2女）' ,
                        `mobile` varchar(20)   COMMENT '手机号' ,
                        `mail` varchar(200)   COMMENT '邮箱' ,
                        `account` varchar(50)   COMMENT '账号' ,
                        `login_password` varchar(32)   COMMENT '登陆密码' ,
                        `last_attempt_login_request_ip` varchar(100)   COMMENT '最后尝试请求IP' ,
                        `last_attempt_login_time` datetime   COMMENT '最后尝试登陆时间' ,
                        `attempt_login_number` tinyint unsigned   COMMENT '尝试登陆次数' ,
                        `request_ip` varchar(100)   COMMENT '常用请求IP' ,
                        `current_request_ip` varchar(100)   COMMENT '当前请求IP' ,
                        `remark` varchar(1000)   COMMENT '备注' ,
                        `create_time` datetime   COMMENT '创建时间' ,
                        `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                        `creator_name` varchar(50)   COMMENT '创建人名称' ,
                        `update_time` datetime   COMMENT '更新时间' ,
                        `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                        `updater_name` varchar(50)   COMMENT '更新人名称' ,
                        `state` tinyint unsigned   COMMENT '状态;（0.正常、1.冻结、99.删除）' ,
                        PRIMARY KEY (id)
)  COMMENT = '管理员';

-- 管理员 1条
INSERT INTO `manager` (`id`, `head_img_url`, `manager_name`, `age`, `sex`, `mobile`, `mail`, `account`, `login_password`, `last_attempt_login_request_ip`, `last_attempt_login_time`, `attempt_login_number`, `request_ip`, `current_request_ip`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`) VALUES (1,'http://localhost:10000/build/1/user-head-image/346c57e020f043eba9de75e90693b264.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=Fi0jJTQMwab11bPSLaxv%2F20250626%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250626T134935Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=6b3869b1623bcff871270f0232c6bfa7d031319266426b820708e3241122aa08','超级管理员',77,0,'13581876056','fuyongyuan277@sina.cn','admin','c4ca4238a0b923820dcc509a6f75849b','0:0:0:0:0:0:0:1','2025-07-01 09:29:07',0,NULL,'0:0:0:0:0:0:0:1','超级管理员','2019-10-09 17:23:08',1,'超级管理员','2025-07-01 09:29:08',1,'超级管理员',0);