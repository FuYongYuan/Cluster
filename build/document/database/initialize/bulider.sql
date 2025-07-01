DROP TABLE IF EXISTS commonly_version;
CREATE TABLE commonly_version(
                                 `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                                 `manager_id` bigint unsigned   COMMENT '管理员ID' ,
                                 `jdk_version` varchar(100)   COMMENT 'JDK版本' ,
                                 `spring_cloud_version` varchar(100)   COMMENT 'SpringCloudVersion' ,
                                 `spring_cloud_alibaba_version` varchar(100)   COMMENT 'SpringCloudAlibabaVersion' ,
                                 `spring_boot_version` varchar(100)   COMMENT 'SpringBootVersion' ,
                                 `open_api_version` varchar(100)   COMMENT 'API文档组件' ,
                                 `lombok_version` varchar(100)   COMMENT '简化对象lombok' ,
                                 `druid_version` varchar(100)   COMMENT '数据访问监控Druid' ,
                                 `mybatis_version` varchar(100)   COMMENT '数据库框架MyBatis版本' ,
                                 `create_time` datetime   COMMENT '创建时间' ,
                                 `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                                 `creator_name` varchar(50)   COMMENT '创建人名称' ,
                                 `update_time` datetime   COMMENT '更新时间' ,
                                 `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                                 `updater_name` varchar(50)   COMMENT '更新人名称' ,
                                 `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                                 PRIMARY KEY (id)
)  COMMENT = '常用版本';

DROP TABLE IF EXISTS project;
CREATE TABLE project(
                        `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                        `project_group_id` bigint unsigned   COMMENT '项目群ID' ,
                        `project_name` varchar(200)   COMMENT '项目名称' ,
                        `artifact_id` varchar(100)   COMMENT '工件ID' ,
                        `package_name` varchar(100)   COMMENT '项目包目录' ,
                        `project_version` varchar(100)   COMMENT '项目版本' ,
                        `project_description` varchar(4000)   COMMENT '项目的描述' ,
                        `project_url` varchar(100)   COMMENT '项目访问路径' ,
                        `project_port` varchar(5)   COMMENT '项目访问端口' ,
                        `is_service_interface` tinyint unsigned   COMMENT '是否Service创建接口;（0.否、1.是）' ,
                        `is_delete_bo` tinyint unsigned   COMMENT '是否去除BO层;（0.否、1.是）' ,
                        `is_basis_method` tinyint unsigned   COMMENT '是否生成基础方法;（0.否、1.是）' ,
                        `is_redis` tinyint unsigned   COMMENT '是否使用Redis缓存;（0.否、1.是）' ,
                        `is_minio` tinyint unsigned   COMMENT '是否使用MinIO文件服务器;（0.否、1.是）' ,
                        `is_mail` tinyint unsigned   COMMENT '是否使用Mail能力;（0.否、1.是）' ,
                        `is_dispose` tinyint unsigned   COMMENT '是否使用内容处理能力;（0.否、1.是）' ,
                        `is_encrypt` tinyint unsigned   COMMENT '是否使用加密能力;（0.否、1.是）' ,
                        `is_excel` tinyint unsigned   COMMENT '是否使用Excel能力;（0.否、1.是）' ,
                        `druid_url` varchar(200)   COMMENT 'Druid访问路径' ,
                        `druid_account` varchar(50)   COMMENT 'Druid登录账号' ,
                        `druid_password` varchar(50)   COMMENT 'Druid登录密码' ,
                        `is_delete_not_pk` tinyint unsigned   COMMENT '是否去除无主键的表;（0.否、1.是）' ,
                        `is_order_key` tinyint unsigned   COMMENT '是否吧主键生成时放第一位;（0.否、1.是）' ,
                        `data_base_framework` tinyint unsigned   COMMENT '数据层框架类型;（0.不使用数据源、1.JPA、2.Mybatis）' ,
                        `data_base_format` tinyint unsigned   COMMENT '数据库格式设定;（0.没有格式转换、1.X_X_Xxxx => XXXXxxx）' ,
                        `create_time` datetime   COMMENT '创建时间' ,
                        `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                        `creator_name` varchar(50)   COMMENT '创建人名称' ,
                        `update_time` datetime   COMMENT '更新时间' ,
                        `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                        `updater_name` varchar(50)   COMMENT '更新人名称' ,
                        `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                        PRIMARY KEY (id)
)  COMMENT = '项目';

DROP TABLE IF EXISTS project_group;
CREATE TABLE project_group(
                              `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                              `manager_id` bigint unsigned   COMMENT '管理员ID' ,
                              `project_group_name` varchar(200)   COMMENT '项目群名称' ,
                              `group_id` varchar(100)   COMMENT '组ID' ,
                              `artifact_id` varchar(100)   COMMENT '工件ID' ,
                              `version` varchar(100)   COMMENT '项目群版本号' ,
                              `jdk_version` varchar(100)   COMMENT 'JDK版本' ,
                              `spring_cloud_version` varchar(100)   COMMENT 'SpringCloudVersion' ,
                              `spring_cloud_alibaba_version` varchar(100)   COMMENT 'SpringCloudAlibabaVersion' ,
                              `spring_boot_version` varchar(100)   COMMENT 'SpringBootVersion' ,
                              `open_api_version` varchar(100)   COMMENT 'API文档组件' ,
                              `lombok_version` varchar(100)   COMMENT '简化对象lombok' ,
                              `druid_version` varchar(100)   COMMENT '数据访问监控Druid' ,
                              `mybatis_version` varchar(100)   COMMENT '数据库框架MyBatis版本' ,
                              `remark` varchar(1000)   COMMENT '备注' ,
                              `create_time` datetime   COMMENT '创建时间' ,
                              `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                              `creator_name` varchar(50)   COMMENT '创建人名称' ,
                              `update_time` datetime   COMMENT '更新时间' ,
                              `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                              `updater_name` varchar(50)   COMMENT '更新人名称' ,
                              `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                              PRIMARY KEY (id)
)  COMMENT = '项目群';

DROP TABLE IF EXISTS project_table;
CREATE TABLE project_table(
                              `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                              `project_group_id` bigint unsigned   COMMENT '项目群ID' ,
                              `data_base_file_id` bigint unsigned   COMMENT '数据库设计文件ID' ,
                              `table_schema` varchar(200)   COMMENT '所属的库' ,
                              `table_name` varchar(200)   COMMENT '表名称' ,
                              `table_comment` varchar(1000)   COMMENT '表注释' ,
                              `create_time` datetime   COMMENT '创建时间' ,
                              `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                              `creator_name` varchar(50)   COMMENT '创建人名称' ,
                              `update_time` datetime   COMMENT '更新时间' ,
                              `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                              `updater_name` varchar(50)   COMMENT '更新人名称' ,
                              `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                              PRIMARY KEY (id)
)  COMMENT = '项目表';

DROP TABLE IF EXISTS project_column;
CREATE TABLE project_column(
                               `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                               `project_table_id` bigint unsigned   COMMENT '项目表ID' ,
                               `table_schema` varchar(200)   COMMENT '所属的库' ,
                               `table_name` varchar(200)   COMMENT '表名称' ,
                               `column_name` varchar(200)   COMMENT '字段名' ,
                               `is_nullable` varchar(5)   COMMENT '是否可以为空' ,
                               `data_type` varchar(20)   COMMENT '数据类型;不包括长度' ,
                               `character_maximum_length` int   COMMENT '数据长度varchar' ,
                               `numeric_precision` int   COMMENT '数字类型的整型部分长度' ,
                               `numeric_scale` int   COMMENT '数字类型的小数部分长度' ,
                               `column_type` varchar(200)   COMMENT '字段类型' ,
                               `column_key` varchar(5)   COMMENT 'PRI为主键，MUL为外键' ,
                               `extra` varchar(200)   COMMENT '自增标识' ,
                               `column_comment` varchar(1000)   COMMENT '字段注释' ,
                               `create_time` datetime   COMMENT '创建时间' ,
                               `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                               `creator_name` varchar(50)   COMMENT '创建人名称' ,
                               `update_time` datetime   COMMENT '更新时间' ,
                               `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                               `updater_name` varchar(50)   COMMENT '更新人名称' ,
                               `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                               PRIMARY KEY (id)
)  COMMENT = '项目表字段';

DROP TABLE IF EXISTS data_base_file;
CREATE TABLE data_base_file(
                               `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                               `project_group_id` bigint unsigned   COMMENT '项目群ID' ,
                               `file_url` varchar(200)   COMMENT '文件地址' ,
                               `create_time` datetime   COMMENT '创建时间' ,
                               `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                               `creator_name` varchar(50)   COMMENT '创建人名称' ,
                               `update_time` datetime   COMMENT '更新时间' ,
                               `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                               `updater_name` varchar(50)   COMMENT '更新人名称' ,
                               `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                               PRIMARY KEY (id)
)  COMMENT = '数据库设计文件';

DROP TABLE IF EXISTS project_project_table;
CREATE TABLE project_project_table(
                                      `id` INT AUTO_INCREMENT COMMENT '主键ID' ,
                                      `project_id` bigint unsigned   COMMENT '项目ID' ,
                                      `project_table_id` bigint unsigned   COMMENT '项目表ID' ,
                                      `create_time` datetime   COMMENT '创建时间' ,
                                      `creator_id` bigint unsigned   COMMENT '创建人ID' ,
                                      `creator_name` varchar(50)   COMMENT '创建人名称' ,
                                      `update_time` datetime   COMMENT '更新时间' ,
                                      `updater_id` bigint unsigned   COMMENT '更新人ID' ,
                                      `updater_name` varchar(50)   COMMENT '更新人名称' ,
                                      `state` tinyint unsigned   COMMENT '状态;（0.正常、99.删除）' ,
                                      PRIMARY KEY (id)
)  COMMENT = '项目项目表关系';
