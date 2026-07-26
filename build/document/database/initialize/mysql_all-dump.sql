-- MySQL dump 10.13  Distrib 9.5.0, for Linux (aarch64)
--
-- Host: 127.0.0.1    Database: authorization
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '4540c393-835e-11f1-bd97-8af16e3513cc:1-28762';

--
-- Current Database: `authorization`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `authorization` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `authorization`;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6508370869929984,'超级管理员','超级管理员','2019-12-04 11:09:27',6508370886681600,'超级管理员','2019-12-09 16:59:37',6508370886681600,'超级管理员',0,0),(6514166638888960,'普通用户','普通用户','2019-12-10 17:28:58',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_button`
--

DROP TABLE IF EXISTS `role_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_button` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `button_id` bigint DEFAULT NULL COMMENT '按钮ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色按钮关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_button`
--

LOCK TABLES `role_button` WRITE;
/*!40000 ALTER TABLE `role_button` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_manager`
--

DROP TABLE IF EXISTS `role_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_manager` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `manager_id` bigint DEFAULT NULL COMMENT '管理员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色管理员关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_manager`
--

LOCK TABLES `role_manager` WRITE;
/*!40000 ALTER TABLE `role_manager` DISABLE KEYS */;
INSERT INTO `role_manager` (`id`, `role_id`, `manager_id`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6514166622111744,6508370869929984,6508370886681600,'2019-12-16 22:41:25',6508370886681600,'超级管理员','2019-12-16 22:41:25',6508370886681600,'超级管理员',0,0);
/*!40000 ALTER TABLE `role_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色菜单关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6508370886707201,6508370869929984,6508370886732800,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),(6508370886707202,6508370869929984,6508370886732801,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),(6508370886707203,6508370869929984,6508370886732803,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),(6508370886707204,6508370869929984,6508370886732804,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),(6508370886707205,6508370869929984,6508370886732805,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),(6508370886707206,6508370869929984,6508370886732806,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),(6508370886707207,6508370869929984,6508370886732802,'2019-12-09 16:59:38',6508370886681600,'超级管理员','2019-12-09 16:59:38',6508370886681600,'超级管理员',0,0),(6508370886707208,6508370869929984,6508370886732807,'2019-12-09 16:59:39',6508370886681600,'超级管理员','2019-12-09 16:59:39',6508370886681600,'超级管理员',0,0),(6508370886707209,6508370869929984,6508370886732808,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),(6508370886707210,6514166638888960,6508370886732800,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),(6508370886707211,6514166638888960,6508370886732802,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),(6508370886707212,6514166638888960,6508370886732807,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0),(6508370886707213,6514166638888960,6508370886732808,'2019-12-10 17:29:53',6508370886681600,'超级管理员','2019-12-10 17:29:53',6508370886681600,'超级管理员',0,0);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) NOT NULL COMMENT '全局事务id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，例如序列化',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int NOT NULL COMMENT '状态 0:正常的状态,1:防御状态',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式撤销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `builder`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `builder` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `builder`;

--
-- Table structure for table `commonly_version`
--

DROP TABLE IF EXISTS `commonly_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commonly_version` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `manager_id` bigint DEFAULT NULL COMMENT '管理员ID',
  `jdk_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JDK版本',
  `spring_cloud_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SpringCloudVersion',
  `spring_cloud_alibaba_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SpringCloudAlibabaVersion',
  `spring_boot_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SpringBootVersion',
  `open_api_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'API文档组件',
  `lombok_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简化对象lombok',
  `druid_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据访问监控Druid',
  `mybatis_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库框架MyBatis版本',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='常用版本';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commonly_version`
--

LOCK TABLES `commonly_version` WRITE;
/*!40000 ALTER TABLE `commonly_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `commonly_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_base_file`
--

DROP TABLE IF EXISTS `data_base_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_base_file` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `project_group_id` bigint DEFAULT NULL COMMENT '项目群ID',
  `file_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据库设计文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_base_file`
--

LOCK TABLES `data_base_file` WRITE;
/*!40000 ALTER TABLE `data_base_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_base_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `project_group_id` bigint DEFAULT NULL COMMENT '项目群ID',
  `project_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目名称',
  `artifact_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工件ID',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目包目录',
  `project_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目版本',
  `project_description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目的描述',
  `project_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目访问路径',
  `project_port` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目访问端口',
  `is_service_interface` tinyint DEFAULT NULL COMMENT '是否Service创建接口;（0.否、1.是）',
  `is_delete_bo` tinyint DEFAULT NULL COMMENT '是否去除BO层;（0.否、1.是）',
  `is_basis_method` tinyint DEFAULT NULL COMMENT '是否生成基础方法;（0.否、1.是）',
  `is_redis` tinyint DEFAULT NULL COMMENT '是否使用Redis缓存;（0.否、1.是）',
  `is_minio` tinyint DEFAULT NULL COMMENT '是否使用MinIO文件服务器;（0.否、1.是）',
  `is_mail` tinyint DEFAULT NULL COMMENT '是否使用Mail能力;（0.否、1.是）',
  `is_dispose` tinyint DEFAULT NULL COMMENT '是否使用内容处理能力;（0.否、1.是）',
  `is_encrypt` tinyint DEFAULT NULL COMMENT '是否使用加密能力;（0.否、1.是）',
  `is_excel` tinyint DEFAULT NULL COMMENT '是否使用Excel能力;（0.否、1.是）',
  `druid_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Druid访问路径',
  `druid_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Druid登录账号',
  `druid_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Druid登录密码',
  `is_delete_not_pk` tinyint DEFAULT NULL COMMENT '是否去除无主键的表;（0.否、1.是）',
  `is_order_key` tinyint DEFAULT NULL COMMENT '是否吧主键生成时放第一位;（0.否、1.是）',
  `data_base_framework` tinyint DEFAULT NULL COMMENT '数据层框架类型;（0.不使用数据源、1.JPA、2.Mybatis）',
  `data_base_format` tinyint DEFAULT NULL COMMENT '数据库格式设定;（0.没有格式转换、1.X_X_Xxxx => XXXXxxx）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_column`
--

DROP TABLE IF EXISTS `project_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_column` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `project_table_id` bigint DEFAULT NULL COMMENT '项目表ID',
  `table_schema` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属的库',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表名称',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段名',
  `is_nullable` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否可以为空',
  `data_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据类型;不包括长度',
  `character_maximum_length` int DEFAULT NULL COMMENT '数据长度varchar',
  `numeric_precision` int DEFAULT NULL COMMENT '数字类型的整型部分长度',
  `numeric_scale` int DEFAULT NULL COMMENT '数字类型的小数部分长度',
  `column_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段类型',
  `column_key` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'PRI为主键，MUL为外键',
  `extra` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '自增标识',
  `column_comment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段注释',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='项目表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_column`
--

LOCK TABLES `project_column` WRITE;
/*!40000 ALTER TABLE `project_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_group`
--

DROP TABLE IF EXISTS `project_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_group` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `manager_id` bigint DEFAULT NULL COMMENT '管理员ID',
  `project_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目群名称',
  `group_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组ID',
  `artifact_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工件ID',
  `group_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项目群版本号',
  `jdk_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JDK版本',
  `spring_cloud_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SpringCloudVersion',
  `spring_cloud_alibaba_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SpringCloudAlibabaVersion',
  `spring_boot_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SpringBootVersion',
  `open_api_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'API文档组件',
  `lombok_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简化对象lombok',
  `druid_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据访问监控Druid',
  `mybatis_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库框架MyBatis版本',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='项目群';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_group`
--

LOCK TABLES `project_group` WRITE;
/*!40000 ALTER TABLE `project_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_project_table`
--

DROP TABLE IF EXISTS `project_project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_project_table` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `project_id` bigint DEFAULT NULL COMMENT '项目ID',
  `project_table_id` bigint DEFAULT NULL COMMENT '项目表ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='项目项目表关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_project_table`
--

LOCK TABLES `project_project_table` WRITE;
/*!40000 ALTER TABLE `project_project_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_project_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_table`
--

DROP TABLE IF EXISTS `project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_table` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `project_group_id` bigint DEFAULT NULL COMMENT '项目群ID',
  `data_base_file_id` bigint DEFAULT NULL COMMENT '数据库设计文件ID',
  `table_schema` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属的库',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表名称',
  `table_comment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表注释',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_table`
--

LOCK TABLES `project_table` WRITE;
/*!40000 ALTER TABLE `project_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) NOT NULL COMMENT '全局事务id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，例如序列化',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int NOT NULL COMMENT '状态 0:正常的状态,1:防御状态',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式撤销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `capability`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `capability` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `capability`;

--
-- Table structure for table `api`
--

DROP TABLE IF EXISTS `api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `api` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `class_name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类名',
  `method_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名',
  `mapping` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Mapping值',
  `summary` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Swagger说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='API信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api`
--

LOCK TABLES `api` WRITE;
/*!40000 ALTER TABLE `api` DISABLE KEYS */;
INSERT INTO `api` (`id`, `class_name`, `method_name`, `mapping`, `summary`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (19896800207252480,'cn.fyy.capability.restcontroller.MenuRestController','queryAllParent','/capability/menu/query/all/parent','查询父级菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835392,'cn.fyy.capability.restcontroller.ApiRestController','save','/capability/api/save','保存 Api 信息','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835393,'cn.fyy.authorization.restcontroller.SystemRestController','logout','/authorization/system/logout','登出','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835394,'cn.fyy.authorization.restcontroller.RoleRestController','queryManagerHaveRoleByManagerId','/authorization/role/query/manager/have/role/{managerId}','根据管理员 ID 查询拥有的角色列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835395,'cn.fyy.member.restcontroller.ManagerRestController','updateLoginPasswordByMail','/member/manager/update/login/password/{mail}/{loginPassword}/{authentication}','根据账号查询尝试登录次数','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-02-24 21:46:52',6508370886681600,'系统默认超级管理员',0,1),(19896800219835396,'cn.fyy.authorization.restcontroller.RoleRestController','queryPageByRoleNameAndState','/authorization/role/query/{currentPage}/{eachPageSize}','根据名称查询角色列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835397,'cn.fyy.capability.restcontroller.MenuRestController','save','/capability/menu/save','保存菜单','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835398,'cn.fyy.message.restcontroller.MailRestController','sendCaptcha','/message/mail/send/captcha/{mail}/{imageCaptcha}/{imageCaptchaCacheName}','发送邮箱验证码','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835399,'cn.fyy.capability.restcontroller.MenuRestController','feignQueryMenuByMenuIdList','/capability/menu/feign/query/menu/list/{menuIdList}/{authentication}','根据菜单 ID 集合查询菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835400,'cn.fyy.message.restcontroller.NoticeRestController','delete','/message/notice/delete/{ids}','根据主键删除 主键可以是多个用,分割','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835401,'cn.fyy.member.restcontroller.ManagerRestController','getByAccountAndLoginPassword','/member/manager/get/account/password/{account}/{loginPassword}','根据账号密码查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835402,'cn.fyy.capability.restcontroller.MenuRestController','queryAll','/capability/menu/query/all','查询菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835403,'cn.fyy.member.restcontroller.ManagerRestController','saveReturnDTO','/member/manager/save/return/dto/{authentication}','保存管理员','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-02-24 21:46:52',6508370886681600,'系统默认超级管理员',0,1),(19896800219835404,'cn.fyy.message.restcontroller.ImageRestController','getImageCaptcha','/message/image/get/captcha','获取图形验证码','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835405,'cn.fyy.data.restcontroller.FileRestController','feignGetFileTemporaryUrl','/data/file/feign/get/file/temporary/url/{managerId}/{businessType}/{fileName}/{duration}/{authentication}','查询文件临时访问地址','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835406,'cn.fyy.authorization.restcontroller.SystemRestController','recover','/authorization/system/recover/{mail}/{loginPassword}/{mailCaptcha}/{mailCaptchaCacheName}','根据电子邮箱、密码找回密码','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835407,'cn.fyy.data.restcontroller.FileRestController','fileUploadReturnUrl','/data/file/upload/return/url/{businessType}','上传文件','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835408,'cn.fyy.message.restcontroller.NoticeRestController','getById','/message/notice/get/{id}','根据主键查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835409,'cn.fyy.member.restcontroller.ManagerRestController','updateStateById','/member/manager/update/state/{ids}/{state}','根据 ID 保存状态','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835410,'cn.fyy.authorization.restcontroller.SystemRestController','queryManagerHaveMenuByJwtToken','/authorization/system/query/manager/have/menu','根据 JWTToken 查询拥有的菜单权限','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835411,'cn.fyy.authorization.restcontroller.RoleMenuRestController','saveList','/authorization/role/menu/save/list/{roleId}/{menuIds}','保存角色菜单关系集合','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835412,'cn.fyy.dictionary.restcontroller.ParameterRestController','save','/dictionary/parameter/save','保存参数','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835413,'cn.fyy.authorization.restcontroller.RoleManagerRestController','saveList','/authorization/role/manager/save/list/{managerId}/{roleIds}','保存角色管理员关系集合','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835414,'cn.fyy.authorization.restcontroller.RoleRestController','getById','/authorization/role/get/{id}','根据主键查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835415,'cn.fyy.member.restcontroller.ManagerRestController','getByMailIsExist','/member/manager/get/mail/exist/{mail}','根据邮箱查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835416,'cn.fyy.member.restcontroller.ManagerRestController','getAttemptLoginNumberByAccount','/member/manager/get/attempt/login/number/{account}','根据账号查询尝试登录次数','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835417,'cn.fyy.member.restcontroller.ManagerRestController','feignUpdateLoginPasswordByMail','/member/manager/feign/update/login/password/{mail}/{loginPassword}/{authentication}','根据账号查询尝试登录次数','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835418,'cn.fyy.message.restcontroller.NoticeRestController','save','/message/notice/save','保存公告','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835419,'cn.fyy.member.restcontroller.ManagerRestController','getById','/member/manager/get/{id}','根据主键查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835420,'cn.fyy.capability.restcontroller.MenuRestController','queryHierarchyMenuByMenuIdList','/capability/menu/query/hierarchy/menu/list/{menuIdList}','根据菜单 ID 集合查询菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835421,'cn.fyy.authorization.restcontroller.RoleRestController','queryAll','/authorization/role/query/all','查询全部角色列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835422,'cn.fyy.dictionary.restcontroller.ParameterRestController','getByParameterCode','/dictionary/parameter/get/parameter/code/{parameterCode}','根据主键查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835423,'cn.fyy.member.restcontroller.ManagerRestController','delete','/member/manager/delete/{ids}','根据主键删除 主键可以是多个用,分割','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835424,'cn.fyy.member.restcontroller.ManagerRestController','feignSaveReturnDTO','/member/manager/feign/save/return/dto/{authentication}','保存管理员','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835425,'cn.fyy.data.restcontroller.FileRestController','fileUpload','/data/file/upload/{businessType}','上传文件','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835426,'cn.fyy.capability.restcontroller.MenuRestController','queryByMenuNameAndState','/capability/menu/query/{currentPage}/{eachPageSize}','根据名称查询菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835427,'cn.fyy.capability.restcontroller.MenuRestController','delete','/capability/menu/delete/{ids}','根据主键删除 主键可以是多个用,分割','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835428,'cn.fyy.authorization.restcontroller.RoleMenuRestController','queryByRoleIdOrderByUpdateTimeAsc','/authorization/role/menu/query/list/{roleId}','根据角色 ID 角色菜单关系集合','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835429,'cn.fyy.capability.restcontroller.ApiRestController','feignSaveList','/capability/api/feign/save/list/{authentication}','保存 Api 信息集合','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835430,'cn.fyy.authorization.restcontroller.RoleRestController','delete','/authorization/role/delete/{ids}','根据主键删除 主键可以是多个用,分割','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835431,'cn.fyy.data.restcontroller.FileRestController','getFileTemporaryUrl','/data/file/get/file/temporary/url/{businessType}/{fileName}/{duration}','查询文件临时访问地址','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835432,'cn.fyy.member.restcontroller.ManagerRestController','getByJwtToken','/member/manager/get/jwt/token','根据 JwtToken 查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835433,'cn.fyy.authorization.restcontroller.SystemRestController','login','/authorization/system/login/{account}/{loginPassword}/{imageCaptcha}/{imageCaptchaCacheName}','根据电子邮箱、账号、密码登录管理员','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835434,'cn.fyy.dictionary.restcontroller.ParameterRestController','queryByParameterCodeAndParameterNameAndState','/dictionary/parameter/query/{currentPage}/{eachPageSize}','根据参数编码、参数名称查询参数列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835435,'cn.fyy.capability.restcontroller.MenuRestController','feignQueryHierarchyMenuByMenuIdList','/capability/menu/feign/query/hierarchy/menu/list/{menuIdList}/{authentication}','根据菜单 ID 集合查询菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835436,'cn.fyy.member.restcontroller.ManagerRestController','save','/member/manager/save','保存管理员','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835437,'cn.fyy.member.restcontroller.ManagerRestController','getByAccountIsExist','/member/manager/get/account/exist/{account}','根据账号查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835438,'cn.fyy.data.restcontroller.FileRestController','fileUploadReturnFileName','/data/file/upload/return/file/name/{businessType}','上传文件','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835439,'cn.fyy.capability.restcontroller.MenuRestController','getById','/capability/menu/get/{id}','根据主键查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835440,'cn.fyy.authorization.restcontroller.SystemRestController','register','/authorization/system/register/{mail}/{account}/{loginPassword}/{mailCaptcha}/{mailCaptchaCacheName}','根据账号密码创建新管理员','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835441,'cn.fyy.authorization.restcontroller.RoleMenuRestController','save','/authorization/role/menu/save','保存角色菜单关系','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835442,'cn.fyy.member.restcontroller.ManagerRestController','queryByAccountAndMobileAndEmailAndUserNameAndState','/member/manager/query/{currentPage}/{eachPageSize}','根据账户、手机、邮箱、管理员名称查询管理员列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835443,'cn.fyy.capability.restcontroller.MenuRestController','queryMenuByMenuIdList','/capability/menu/query/menu/list/{menuIdList}','根据菜单 ID 集合查询菜单列表','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835444,'cn.fyy.authorization.restcontroller.RoleRestController','save','/authorization/role/save','保存角色','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835445,'cn.fyy.dictionary.restcontroller.ParameterRestController','delete','/dictionary/parameter/delete/{ids}','根据主键删除 主键可以是多个用,分割','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835446,'cn.fyy.dictionary.restcontroller.ParameterRestController','export','/dictionary/parameter/export','导出','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835447,'cn.fyy.dictionary.restcontroller.ParameterRestController','getById','/dictionary/parameter/get/{id}','根据主键查询','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134),(19896800219835448,'cn.fyy.authorization.restcontroller.RoleManagerRestController','save','/authorization/role/manager/save','保存角色管理员关系','2026-02-24 21:42:47',6508370886681600,'系统默认超级管理员','2026-07-22 16:47:00',6508370886681600,'系统默认超级管理员',0,134);
/*!40000 ALTER TABLE `api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `button`
--

DROP TABLE IF EXISTS `button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `button` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `button_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `button_sign` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标识',
  `on_click` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '点击事件',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  `button_order` int DEFAULT NULL COMMENT '排序;按数字从小到大',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='按钮';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `button`
--

LOCK TABLES `button` WRITE;
/*!40000 ALTER TABLE `button` DISABLE KEYS */;
/*!40000 ALTER TABLE `button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `menu_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `menu_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路径',
  `menu_icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `page_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '页面名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父级ID',
  `is_turn` tinyint DEFAULT NULL COMMENT '是否跳转;（0.否、1.是）',
  `is_home` tinyint DEFAULT NULL COMMENT '是否首页;（0.否、1.是）',
  `menu_order` int DEFAULT NULL COMMENT '排序;按数字从小到大',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`, `menu_name`, `menu_url`, `menu_icon`, `page_name`, `parent_id`, `is_turn`, `is_home`, `menu_order`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6508370886732800,'首页','/home','icon-home','Home',NULL,1,1,100,'首页','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 16:41:56',6508370886681600,'超级管理员',0,0),(6508370886732801,'系统管理',NULL,'icon-desktop',NULL,NULL,0,0,101,'系统管理操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 22:38:03',6508370886681600,'超级管理员',0,0),(6508370886732802,'项目群管理',NULL,'icon-project',NULL,NULL,0,0,102,'项目群操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 22:38:03',6508370886681600,'超级管理员',0,0),(6508370886732803,'菜单管理','/menu/manage','icon-menu','MenuManage',6508370886732801,1,0,10101,'菜单操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-02 21:50:17',6508370886681600,'超级管理员',0,0),(6508370886732804,'角色管理','/role/manage','icon-audit','RoleManage',6508370886732801,1,0,10102,'角色操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-11-29 11:28:07',6508370886681600,'超级管理员',0,0),(6508370886732805,'用户管理','/manager/manage','icon-user','ManagerManage',6508370886732801,1,0,10103,'用户操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-11-29 11:28:07',6508370886681600,'超级管理员',0,0),(6508370886732806,'参数管理','/parameter/manage','icon-wrench','ParameterManage',6508370886732801,1,0,10104,'参数设置操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 16:41:56',6508370886681600,'超级管理员',0,0),(6508370886732807,'项目群管理','/group/manage','icon-project','GroupManage',6508370886732802,1,0,10201,'项目群操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-19 17:09:48',6508370886681600,'超级管理员',0,0),(6508370886732808,'新增项目群','/group/detail','icon-file-add','GroupDetail',6508370886732802,1,0,10202,'新增项目群操作','2019-11-29 11:27:59',6508370886681600,'超级管理员','2019-12-16 16:41:56',6508370886681600,'超级管理员',0,0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) NOT NULL COMMENT '全局事务id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，例如序列化',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int NOT NULL COMMENT '状态 0:正常的状态,1:防御状态',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式撤销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `dictionary`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dictionary` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dictionary`;

--
-- Table structure for table `parameter`
--

DROP TABLE IF EXISTS `parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parameter` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `parameter_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数代码',
  `parameter_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数名称',
  `parameter_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '参数值',
  `parameter_explain` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='参数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameter`
--

LOCK TABLES `parameter` WRITE;
/*!40000 ALTER TABLE `parameter` DISABLE KEYS */;
INSERT INTO `parameter` (`id`, `parameter_code`, `parameter_name`, `parameter_value`, `parameter_explain`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6508370886758400,'CreateProjectGroupMaxNumber','创建项目群最大数','3','用于限制个人创建项目群最大数量','2019-12-30 09:20:07',6508370886681600,'超级管理员','2020-01-03 23:25:02',6508370886681600,'超级管理员',0,0);
/*!40000 ALTER TABLE `parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) NOT NULL COMMENT '全局事务id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，例如序列化',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int NOT NULL COMMENT '状态 0:正常的状态,1:防御状态',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式撤销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `member`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `member` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `member`;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `manager_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `age` tinyint DEFAULT NULL COMMENT '年龄',
  `sex` tinyint DEFAULT NULL COMMENT '性别;（0.保密、1.男、2女）',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `mail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号',
  `login_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登陆密码',
  `last_attempt_login_request_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后尝试请求IP',
  `last_attempt_login_time` datetime DEFAULT NULL COMMENT '最后尝试登陆时间',
  `attempt_login_number` int DEFAULT NULL,
  `request_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '常用请求IP',
  `current_request_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '当前请求IP',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、1.冻结、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` (`id`, `avatar`, `manager_name`, `age`, `sex`, `mobile`, `mail`, `account`, `login_password`, `last_attempt_login_request_ip`, `last_attempt_login_time`, `attempt_login_number`, `request_ip`, `current_request_ip`, `remark`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6508370886681600,'6d4c181237ab4af6ad00c73f8ba74ca5.jpeg','超级管理员',77,0,'13581876056','fuyongyuan277@sina.cn','admin','c4ca4238a0b923820dcc509a6f75849b','172.21.138.206','2026-07-21 20:21:32',0,NULL,'172.21.138.206','超级管理员','2019-10-09 17:23:08',6508370886681600,'超级管理员','2026-07-21 20:21:34',6508370886681600,'超级管理员',0,9);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) NOT NULL COMMENT '全局事务id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，例如序列化',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int NOT NULL COMMENT '状态 0:正常的状态,1:防御状态',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式撤销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `message`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `message` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `message`;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `notice_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告标题',
  `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '公告内容',
  `notice_author` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告作者',
  `notice_order` int DEFAULT NULL COMMENT '公告排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新人ID',
  `updater_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人名称',
  `state` tinyint DEFAULT NULL COMMENT '状态;（0.正常、99.删除）',
  `version` bigint DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`id`, `notice_title`, `notice_content`, `notice_author`, `notice_order`, `create_time`, `creator_id`, `creator_name`, `update_time`, `updater_id`, `updater_name`, `state`, `version`) VALUES (6508370886809600,NULL,NULL,'超级管理员',1,'2019-10-09 17:23:08',6508370886681600,'超级管理员','2020-04-30 11:10:06',6508370886681600,'超级管理员',0,0);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `undo_log`
--

DROP TABLE IF EXISTS `undo_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `undo_log` (
  `branch_id` bigint NOT NULL COMMENT '分支事务id',
  `xid` varchar(128) NOT NULL COMMENT '全局事务id',
  `context` varchar(128) NOT NULL COMMENT 'undo_log上下文，例如序列化',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int NOT NULL COMMENT '状态 0:正常的状态,1:防御状态',
  `log_created` datetime(6) NOT NULL COMMENT '创建时间',
  `log_modified` datetime(6) NOT NULL COMMENT '修改时间',
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`),
  KEY `ix_log_created` (`log_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AT事务模式撤销表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `undo_log`
--

LOCK TABLES `undo_log` WRITE;
/*!40000 ALTER TABLE `undo_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `undo_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `nacos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nacos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nacos`;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'group_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'configuration description',
  `c_use` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'configuration usage',
  `effect` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置生效的描述',
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置的类型',
  `c_schema` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '配置的模式',
  `encrypted_data_key` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (1,'authorization-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n# 鉴权\nauthorization:\n  # aes 密钥\n  aes-key: authorization-fyy\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','bc83b3fe68ee39310ac8554409d8f7b6','2025-12-15 09:45:29','2025-12-15 13:12:04','fyy','192.168.65.1','authorization','7','鉴权中心基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(2,'gateway-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n  cloud:\n    gateway:\n      server:\n        webflux:\n          discovery:\n            locator:\n              # 让gateway可以发现nacos中的微服务\n              enabled: true\n          # 全局的跨域处理\n          globalcors:\n            # 解决options请求被拦截问题\n            add-to-simple-url-handler-mapping: true\n            # 跨域配置\n            corsConfigurations:\n              \'[/**]\':\n                # 允许哪些网站的跨域请求\n                allowedOrigins:\n                  - \"http://localhost:5173\"\n                # 允许的跨域ajax的请求方式\n                allowedMethods:\n                  - \"GET\"\n                  - \"POST\"\n                  - \"DELETE\"\n                  - \"PUT\"\n                  - \"OPTIONS\"\n                # 允许在请求中携带的头信息\n                allowedHeaders: \"*\"\n                # 是否允许携带cookie\n                allowCredentials: true\n                # 这次跨域检测的有效期\n                maxAge: 360000\n          # 路由\n          routes:\n            # 路由的名字-唯一\n            - id: build-member-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://member\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/member/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n            # 路由的名字-唯一\n            - id: build-authorization-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://authorization\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/authorization/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n            # 路由的名字-唯一\n            - id: build-capability-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://capability\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/capability/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n            # 路由的名字-唯一\n            - id: build-dictionary-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://dictionary\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/dictionary/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n            # 路由的名字-唯一\n            - id: build-data-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://data\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/data/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n            # 路由的名字-唯一\n            - id: build-message-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://message\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/message/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n            # 路由的名字-唯一\n            - id: build-builder-route\n              # lb指的是从nacos中按照名称获取微服务,并遵循负载均衡策略\n              uri: lb://builder\n              predicates:\n                # 符合这个规定的才进行1转发\n                - Path=/builder/**\n                # 这个时间之后可以访问\n                #- After=2037-01-20T17:42:47.789-07:00[Asia/Shanghai]\n              filters:\n                # 将第一层去掉\n                - StripPrefix=1\n\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'*\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','6a9545be5b71b943bbee9fdfe73d4203','2025-12-15 09:45:29','2025-12-16 03:08:09','fyy','192.168.65.1','gateway','7','网关基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(3,'jwt-test.yaml','system','# JWT 配置\njwt:\n  # 私钥\n  private-key: |\n    -----BEGIN PRIVATE KEY-----\n    MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDE4+6LTFPezLHIotaJ3Z0nIiDexOnlVJs+zFVs2YVKEj8RZLE91+4FO67lR/C3sQZj8BHTb0UPurDjWFyMt1HmxOty5R5XDgrrsUbBj1Wgn6ue2VzY0mXM0b/y8Kjmj2IaCQh5bzt2+n9YIBJbec7rObSeeipvw4wi5SdTiKRbWgLVmoUOvhoFXVrV9Hfo49S8mTQoBpmeUiO+/rjMew0QGFqARO2+5fWKo/mteHIjHu4J+mI3WDRl7mBaYCsv6EUsBZc/EGfvvEkNeuob+iI0b32rAPTgnHrCOVelPlHwUHr31eRh8Poh/k94cUBh9/U/8jVXzlQJcMFG0Jtc1aUzAgMBAAECggEALResvt9sx/amcvaEXw/P2bIRyCNeFCVD5uaCXAsXYUb7hb7DbxAAldc874sB4PgcqA/H7V4q+Xa+41qN27ZyfgE0kVxTDgoLyz4PH+51ZHOdKSTMBGZvZTDWnsuqSx+kjqTp+XHVsyKcvrCds7CaiLnvGWqORImLE0TbNGDzj24O8Ex4bcjpNa5POGLzco5JlLUJ4HbXf/89c6uS9V8dv2/ev+iRl4JqWFxEc8+0vyFt8rmz36Kf1CImB6D+GWV3Edt7roaHy6zI8A0NV3yz52ROvL/cdCNVljPZWKiOqVF0jrSbc4nlecOB5G9fb3UN2COabqHD2PqKuIUWEvnANQKBgQD8t3a2UsKwOofdO0CHivpvaNcXOpmwPkLVRSDC8QiE7WTaZtsmdXVibqOY1fCkGrpVLWGgwAchPa5prNNQ6o+w9u/7EGiDiBdI+mvoHNSw/eFErj74QJtX5SfZn4K9s8QWx6T9ygQ5+LLtUVyZuTqLiqKnmf4Xfrj/9etqO06vVQKBgQDHcsoodfqg92rrM7MY9a3nEjxx22kklDIKrAcjuto0LVm9n6D/L6ayBLfisgWdzTTDOdAeDE7oy2pI8TGBjFTYIqeXL0qztyP6sjUClZzCJmz2IsposOSzQgO6NDHOCnzJkMi6uSknWubYTuJw1W9aA3hbznCE8sk5uIeueTSyZwKBgErlmLI6XubZwfBoIhzJrKUeHjkNLgVUvnv0i9wakVrL/IrZ1vNT9mEgxy2BolsQbM7Xr0pz/uj0Hd/Fz674hXN+C+uNllv9FmGTji1xdfuqknvHZHQwuZlYGfJeWuIcB+50Zsqsd4oy0dyRs27/vYEjqkcf1XVjIjMB0C5gu1YtAoGAbQE1r4tbYSIYE1f8KEYNt15mHqwV+OHtv5tpg4OZwi6O/24A5bc7WcvuXD2DiE0g6xnrvqBneB/EhP0dW3pA3KeEBMLaBwKu7VL8Wd8+/3VU0+kLys/zDm2j796a4csljFxO+I4+Cb5KsCnc5GIiK4X9/98B7AdtJtjNfvPLcz0CgYBnZROtPfulFSIEXNwL5RMrGgiMBpSMIuXcKcdfGFvhtD9FaDb2j0Ba4GupmWIxPooYN8/XbjRN/E71mipjEEpLxIadJMjsAceR1GVLVEVAfFi3XB5hMZHuJgi6AVcrrCqw2wUPItjkw+X8ur3uRpap6FT4LchTpP2iJK9AgnxIIw==\n    -----END PRIVATE KEY-----\n  # 公钥\n  public-key: |\n    -----BEGIN PUBLIC KEY-----\n    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxOPui0xT3syxyKLWid2dJyIg3sTp5VSbPsxVbNmFShI/EWSxPdfuBTuu5Ufwt7EGY/AR029FD7qw41hcjLdR5sTrcuUeVw4K67FGwY9VoJ+rntlc2NJlzNG/8vCo5o9iGgkIeW87dvp/WCASW3nO6zm0nnoqb8OMIuUnU4ikW1oC1ZqFDr4aBV1a1fR36OPUvJk0KAaZnlIjvv64zHsNEBhagETtvuX1iqP5rXhyIx7uCfpiN1g0Ze5gWmArL+hFLAWXPxBn77xJDXrqG/oiNG99qwD04Jx6wjlXpT5R8FB699XkYfD6If5PeHFAYff1P/I1V85UCXDBRtCbXNWlMwIDAQAB\n    -----END PUBLIC KEY-----\n  # 有效期，单位毫秒秒\n  access-token-expire-time: PT2H\n  # 刷新过期时间\n  refresh-token-expire-time: PT8H\n  # APP刷新过期时间\n  refresh-token-expire-app-time: P30D\n  # JWT存储的请求头\n  token-header: Authorization\n  # JWT负载中拿到开头\n  token-head: Bearer\n  # 签发系统\n  issuer: authorization\n  # 是否输出相关日志\n  show-log: true','d9e1741ec25bd84c88726e6a6901f2dc','2025-12-15 09:45:29','2025-12-15 09:45:29','fyy','192.168.65.1','jwt','7','jwt认证配置-测试环境',NULL,NULL,'yaml',NULL,''),(4,'mail-test.yaml','system','#邮箱配置\nspring:\n  mail:\n    host: smtp.sina.cn\n    username: fuyongyuan277@sina.cn\n    password: 679cf6b8fa36a8cd\n    properties:\n      personal: \'哎呋歪歪平台\'','4933e33fc02a6943f6af8eb9e4234f8b','2025-12-15 09:45:29','2026-07-20 15:28:44','fyy','192.168.65.1','mail','7','mail链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(6,'authorization-data-test.yaml','service','spring:\n  # JPA设置\n  jpa:\n    hibernate:\n      # 运行时对表得操作\n      # create 启动时删数据库中的表，然后创建，退出时不删除数据表\n      # create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错\n      # update 如果启动时表格式不一致则更新表，原有数据保留\n      # validate 项目启动表结构进行校验 如果不一致则报错\n      ddl-auto: update\n    # sql执行是否打印\n    show-sql: true\n    # 设置参数\n    properties:\n      hibernate:\n        # 格式化sql\n        format_sql: fales\n    # 打开视图\n    open-in-view: true\n    # 数据层目录\n    repository-path: \'cn.fyy.authorization.repository\'\n    # 数据模型目录\n    po-path: \'cn.fyy.authorization.bean.po\'\n\n  # 数据源配置\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 拦截器\n      web-stat-filter:\n        # 是否启用\n        enabled: true\n        # 经常需要排除一些不必要的url，比如*.js,/jslib/*等等\n        exclusions: \'*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\'\n        # 配置profileEnable能够监控单个url调用的sql列表\n        profile-enable: true\n        # 关闭session统计功能\n        session-stat-enable: false\n        # 拦截地址\n        url-pattern: /*\n      # 统计配置\n      stat-view-servlet:\n        # 是否启用\n        enabled: true\n        # 统计页面登录账号\n        login-password: fyy\n        # 统计页面登录密码\n        login-username: fyyain77\n        # 是否允许清空统计数据\n        reset-enable: false\n        # 访问地址\n        url-pattern: /druid/*\n        # 允许访问IP\n        allow:\n        # 拒绝访问IP\n        deny:\n      # AOP切面\n      aop-patterns: \'cn.fyy.authorization.service.impl.*,cn.fyy.authorization.repository.*\'\n      # 用户数据库读数据源\n      read:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://localhost:3306/authorization?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 读取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: false\n              # 是否允许执行UPDATE语句\n              update-allow: false\n              # 是否允许执行INSERT语句\n              insert-allow: false\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: false\n              # 是否允许使用SET语法\n              set-allow: false\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: false\n              # 是否允许执行Alter Table语句\n              alter-table-allow: false\n              # 是否允许修改表\n              drop-table-allow: false\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: false\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: false\n              # 是否允许执行roll back操作\n              rollback-allow: false\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n      # 用户数据库写数据源\n      write:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://127.0.0.1:3306/authorization?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 写取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: true\n              # 是否允许执行UPDATE语句\n              update-allow: true\n              # 是否允许执行INSERT语句\n              insert-allow: true\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: true\n              # 是否允许使用SET语法\n              set-allow: true\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: true\n              # 是否允许执行Alter Table语句\n              alter-table-allow: true\n              # 是否允许修改表\n              drop-table-allow: true\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: true\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: true\n              # 是否允许执行roll back操作\n              rollback-allow: true\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n# 雪花算法配置\nsnowflake-id:\n  # 工作实例ID (0~1023)\n  worker-id: 200\n  # 数据中心ID (0~15)\n  data-center-id: 1\n  # 时间起始时间 (2026-01-01 00:00:00.000)\n  epoch: 1767196800000\n  # 时间倒退策略 wait 或 exception ignore\n  clock-backward-strategy: wait\n\n# 分布式事务\nseata:\n  application-id: seata-${spring.application.name}-${spring.profiles.active}\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.discovery.server-addr}\n      username: ${spring.cloud.nacos.discovery.username}\n      password: ${spring.cloud.nacos.discovery.password}\n      namespace: ${spring.cloud.nacos.discovery.namespace}\n      cluster-name: ${spring.cloud.nacos.discovery.cluster-name}\n      # 分组\n      group: system\n      # Nacos 中 Seata 名称\n      application: seata-server\n  # 事务组（被一个集群管理）\n  tx-service-group: seata_tx_group\n  service:\n    vgroup-mapping:\n      # 映射到默认的服务分组-名字同上\n      seata_tx_group: default\n  # 实现 AT 模式\n  data-source-proxy-mode: AT\n','9d144a88c6e7ba81089e8b38387c0390','2025-12-15 09:45:29','2026-01-12 06:14:37','fyy','192.168.65.1','authorization','7','mysql链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(7,'redis-test.yaml','system','spring:\n  data:\n    redis:\n      # Redis服务器地址\n      host: 127.0.0.1\n      # Redis服务器连接端口\n      port: 6379\n      # Redis服务器连接密码（默认为空）\n      password: fyyain77\n      # Redis数据库索引（默认为0）\n      database: 0\n      # 连接空闲超过N(s秒、ms毫秒，不加单位时使用毫秒)后关闭，0为禁用，这里配置值和tcp-keepalive值一致\n      timeout: 60s\n      # Lettuce连接池配置\n      lettuce:\n        pool:\n          # 允许最大连接数，默认8（负值表示没有限制），推荐值：大于cpu * 2，通常为(cpu * 2) + 2\n          max-active: 10\n          # 最大空闲连接数，默认8，推荐值：cpu * 2\n          max-idle: 8\n          # 最小空闲连接数，默认0\n          min-idle: 0\n          # 连接用完时，新的请求等待时间(s秒、ms毫秒)，超过该时间抛出异常，默认-1（负值表示没有限制）\n          max-wait: 5s\n','bd96bb87818f8751b249830724cfd221','2025-12-15 09:45:29','2025-12-15 09:45:29','fyy','192.168.65.1','redis','7','redis链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(8,'builder-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','dc43a80093ec5e3d9fdeeee09f9a2f9f','2025-12-15 09:45:29','2025-12-15 13:13:53','fyy','192.168.65.1','builder','7','构建模块基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(9,'builder-data-test.yaml','service','spring:\n  # JPA设置\n  jpa:\n    hibernate:\n      # 运行时对表得操作\n      # create 启动时删数据库中的表，然后创建，退出时不删除数据表\n      # create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错\n      # update 如果启动时表格式不一致则更新表，原有数据保留\n      # validate 项目启动表结构进行校验 如果不一致则报错\n      ddl-auto: update\n    # sql执行是否打印\n    show-sql: true\n    # 设置参数\n    properties:\n      hibernate:\n        # 格式化sql\n        format_sql: fales\n    # 打开视图\n    open-in-view: true\n    # 数据层目录\n    repository-path: \'cn.fyy.builder.repository\'\n    # 数据模型目录\n    po-path: \'cn.fyy.builder.bean.po\'\n\n  # 数据源配置\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 拦截器\n      web-stat-filter:\n        # 是否启用\n        enabled: true\n        # 经常需要排除一些不必要的url，比如*.js,/jslib/*等等\n        exclusions: \'*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\'\n        # 配置profileEnable能够监控单个url调用的sql列表\n        profile-enable: true\n        # 关闭session统计功能\n        session-stat-enable: false\n        # 拦截地址\n        url-pattern: /*\n      # 统计配置\n      stat-view-servlet:\n        # 是否启用\n        enabled: true\n        # 统计页面登录账号\n        login-password: fyy\n        # 统计页面登录密码\n        login-username: fyyain77\n        # 是否允许清空统计数据\n        reset-enable: false\n        # 访问地址\n        url-pattern: /druid/*\n        # 允许访问IP\n        allow:\n        # 拒绝访问IP\n        deny:\n      # AOP切面\n      aop-patterns: \'cn.fyy.builder.service.impl.*,cn.fyy.builder.repository.*\'\n      # 用户数据库读数据源\n      read:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://localhost:3306/builder?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 读取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: false\n              # 是否允许执行UPDATE语句\n              update-allow: false\n              # 是否允许执行INSERT语句\n              insert-allow: false\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: false\n              # 是否允许使用SET语法\n              set-allow: false\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: false\n              # 是否允许执行Alter Table语句\n              alter-table-allow: false\n              # 是否允许修改表\n              drop-table-allow: false\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: false\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: false\n              # 是否允许执行roll back操作\n              rollback-allow: false\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n      # 用户数据库写数据源\n      write:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://127.0.0.1:3306/builder?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 写取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: true\n              # 是否允许执行UPDATE语句\n              update-allow: true\n              # 是否允许执行INSERT语句\n              insert-allow: true\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: true\n              # 是否允许使用SET语法\n              set-allow: true\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: true\n              # 是否允许执行Alter Table语句\n              alter-table-allow: true\n              # 是否允许修改表\n              drop-table-allow: true\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: true\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: true\n              # 是否允许执行roll back操作\n              rollback-allow: true\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n# 雪花算法配置\nsnowflake-id:\n  # 工作实例ID (0~1023)\n  worker-id: 700\n  # 数据中心ID (0~15)\n  data-center-id: 1\n  # 时间起始时间 (2026-01-01 00:00:00.000)\n  epoch: 1767196800000\n  # 时间倒退策略 wait 或 exception ignore\n  clock-backward-strategy: wait\n\n# 分布式事务\nseata:\n  application-id: seata-${spring.application.name}-${spring.profiles.active}\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.discovery.server-addr}\n      username: ${spring.cloud.nacos.discovery.username}\n      password: ${spring.cloud.nacos.discovery.password}\n      namespace: ${spring.cloud.nacos.discovery.namespace}\n      cluster-name: ${spring.cloud.nacos.discovery.cluster-name}\n      # 分组\n      group: system\n      # Nacos 中 Seata 名称\n      application: seata-server\n  # 事务组（被一个集群管理）\n  tx-service-group: seata_tx_group\n  service:\n    vgroup-mapping:\n      # 映射到默认的服务分组-名字同上\n      seata_tx_group: default\n  # 实现 AT 模式\n  data-source-proxy-mode: AT\n','462b61861c9b95389010df645f450528','2025-12-15 09:45:29','2026-01-12 06:15:07','fyy','192.168.65.1','builder','7','mysql链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(10,'capability-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n# 鉴权\nauthorization:\n  # aes 密钥\n  aes-key: authorization-fyy\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','bc83b3fe68ee39310ac8554409d8f7b6','2025-12-15 09:45:29','2026-02-09 09:06:46','fyy','192.168.65.1','capability','7','能力模块基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(11,'capability-data-test.yaml','service','spring:\n  # JPA设置\n  jpa:\n    hibernate:\n      # 运行时对表得操作\n      # create 启动时删数据库中的表，然后创建，退出时不删除数据表\n      # create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错\n      # update 如果启动时表格式不一致则更新表，原有数据保留\n      # validate 项目启动表结构进行校验 如果不一致则报错\n      ddl-auto: update\n    # sql执行是否打印\n    show-sql: true\n    # 设置参数\n    properties:\n      hibernate:\n        # 格式化sql\n        format_sql: fales\n    # 打开视图\n    open-in-view: true\n    # 数据层目录\n    repository-path: \'cn.fyy.capability.repository\'\n    # 数据模型目录\n    po-path: \'cn.fyy.capability.bean.po\'\n\n  # 数据源配置\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 拦截器\n      web-stat-filter:\n        # 是否启用\n        enabled: true\n        # 经常需要排除一些不必要的url，比如*.js,/jslib/*等等\n        exclusions: \'*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\'\n        # 配置profileEnable能够监控单个url调用的sql列表\n        profile-enable: true\n        # 关闭session统计功能\n        session-stat-enable: false\n        # 拦截地址\n        url-pattern: /*\n      # 统计配置\n      stat-view-servlet:\n        # 是否启用\n        enabled: true\n        # 统计页面登录账号\n        login-password: fyy\n        # 统计页面登录密码\n        login-username: fyyain77\n        # 是否允许清空统计数据\n        reset-enable: false\n        # 访问地址\n        url-pattern: /druid/*\n        # 允许访问IP\n        allow:\n        # 拒绝访问IP\n        deny:\n      # AOP切面\n      aop-patterns: \'cn.fyy.capability.service.impl.*,cn.fyy.capability.repository.*\'\n      # 用户数据库读数据源\n      read:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://localhost:3306/capability?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 读取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: false\n              # 是否允许执行UPDATE语句\n              update-allow: false\n              # 是否允许执行INSERT语句\n              insert-allow: false\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: false\n              # 是否允许使用SET语法\n              set-allow: false\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: false\n              # 是否允许执行Alter Table语句\n              alter-table-allow: false\n              # 是否允许修改表\n              drop-table-allow: false\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: false\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: false\n              # 是否允许执行roll back操作\n              rollback-allow: false\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n      # 用户数据库写数据源\n      write:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://127.0.0.1:3306/capability?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 写取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: true\n              # 是否允许执行UPDATE语句\n              update-allow: true\n              # 是否允许执行INSERT语句\n              insert-allow: true\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: true\n              # 是否允许使用SET语法\n              set-allow: true\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: true\n              # 是否允许执行Alter Table语句\n              alter-table-allow: true\n              # 是否允许修改表\n              drop-table-allow: true\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: true\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: true\n              # 是否允许执行roll back操作\n              rollback-allow: true\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n# 雪花算法配置\nsnowflake-id:\n  # 工作实例ID (0~1023)\n  worker-id: 300\n  # 数据中心ID (0~15)\n  data-center-id: 1\n  # 时间起始时间 (2026-01-01 00:00:00.000)\n  epoch: 1767196800000\n  # 时间倒退策略 wait 或 exception ignore\n  clock-backward-strategy: wait\n\n# 分布式事务\nseata:\n  application-id: seata-${spring.application.name}-${spring.profiles.active}\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.discovery.server-addr}\n      username: ${spring.cloud.nacos.discovery.username}\n      password: ${spring.cloud.nacos.discovery.password}\n      namespace: ${spring.cloud.nacos.discovery.namespace}\n      cluster-name: ${spring.cloud.nacos.discovery.cluster-name}\n      # 分组\n      group: system\n      # Nacos 中 Seata 名称\n      application: seata-server\n  # 事务组（被一个集群管理）\n  tx-service-group: seata_tx_group\n  service:\n    vgroup-mapping:\n      # 映射到默认的服务分组-名字同上\n      seata_tx_group: default\n  # 实现 AT 模式\n  data-source-proxy-mode: AT\n','135f8b96972f1b41e52fb38f7403ad51','2025-12-15 09:45:29','2026-01-12 06:15:36','fyy','192.168.65.1','capability','7','mysql链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(12,'data-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n  servlet:\n    multipart:\n      # 是否启用http上传处理\n      enabled: true\n      # 最大请求文件的大小\n      max-request-size: 100MB\n      # 设置单个文件最大长度\n      max-file-size: 20MB\n      # 当文件达到多少时进行磁盘写入\n      file-size-threshold: 20MB\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n# 鉴权\nauthorization:\n  # aes 密钥\n  aes-key: authorization-fyy\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','5efd351f5b368f6947a5a11f5bdc28e8','2025-12-15 09:45:29','2026-02-09 12:32:19','fyy','192.168.65.1','data','7','数据模块基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(13,'dictionary-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','dc43a80093ec5e3d9fdeeee09f9a2f9f','2025-12-15 09:45:29','2025-12-15 13:14:36','fyy','192.168.65.1','dictionary','7','数据字典模块基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(14,'dictionary-data-test.yaml','service','spring:\n  # JPA设置\n  jpa:\n    hibernate:\n      # 运行时对表得操作\n      # create 启动时删数据库中的表，然后创建，退出时不删除数据表\n      # create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错\n      # update 如果启动时表格式不一致则更新表，原有数据保留\n      # validate 项目启动表结构进行校验 如果不一致则报错\n      ddl-auto: update\n    # sql执行是否打印\n    show-sql: true\n    # 设置参数\n    properties:\n      hibernate:\n        # 格式化sql\n        format_sql: fales\n    # 打开视图\n    open-in-view: true\n    # 数据层目录\n    repository-path: \'cn.fyy.dictionary.repository\'\n    # 数据模型目录\n    po-path: \'cn.fyy.dictionary.bean.po\'\n\n  # 数据源配置\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 拦截器\n      web-stat-filter:\n        # 是否启用\n        enabled: true\n        # 经常需要排除一些不必要的url，比如*.js,/jslib/*等等\n        exclusions: \'*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\'\n        # 配置profileEnable能够监控单个url调用的sql列表\n        profile-enable: true\n        # 关闭session统计功能\n        session-stat-enable: false\n        # 拦截地址\n        url-pattern: /*\n      # 统计配置\n      stat-view-servlet:\n        # 是否启用\n        enabled: true\n        # 统计页面登录账号\n        login-password: fyy\n        # 统计页面登录密码\n        login-username: fyyain77\n        # 是否允许清空统计数据\n        reset-enable: false\n        # 访问地址\n        url-pattern: /druid/*\n        # 允许访问IP\n        allow:\n        # 拒绝访问IP\n        deny:\n      # AOP切面\n      aop-patterns: \'cn.fyy.dictionary.service.impl.*,cn.fyy.dictionary.repository.*\'\n      # 用户数据库读数据源\n      read:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://localhost:3306/dictionary?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 读取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: false\n              # 是否允许执行UPDATE语句\n              update-allow: false\n              # 是否允许执行INSERT语句\n              insert-allow: false\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: false\n              # 是否允许使用SET语法\n              set-allow: false\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: false\n              # 是否允许执行Alter Table语句\n              alter-table-allow: false\n              # 是否允许修改表\n              drop-table-allow: false\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: false\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: false\n              # 是否允许执行roll back操作\n              rollback-allow: false\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n      # 用户数据库写数据源\n      write:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://127.0.0.1:3306/dictionary?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 写取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: true\n              # 是否允许执行UPDATE语句\n              update-allow: true\n              # 是否允许执行INSERT语句\n              insert-allow: true\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: true\n              # 是否允许使用SET语法\n              set-allow: true\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: true\n              # 是否允许执行Alter Table语句\n              alter-table-allow: true\n              # 是否允许修改表\n              drop-table-allow: true\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: true\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: true\n              # 是否允许执行roll back操作\n              rollback-allow: true\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n# 雪花算法配置\nsnowflake-id:\n  # 工作实例ID (0~1023)\n  worker-id: 400\n  # 数据中心ID (0~15)\n  data-center-id: 1\n  # 时间起始时间 (2026-01-01 00:00:00.000)\n  epoch: 1767196800000\n  # 时间倒退策略 wait 或 exception ignore\n  clock-backward-strategy: wait\n\n# 分布式事务\nseata:\n  application-id: seata-${spring.application.name}-${spring.profiles.active}\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.discovery.server-addr}\n      username: ${spring.cloud.nacos.discovery.username}\n      password: ${spring.cloud.nacos.discovery.password}\n      namespace: ${spring.cloud.nacos.discovery.namespace}\n      cluster-name: ${spring.cloud.nacos.discovery.cluster-name}\n      # 分组\n      group: system\n      # Nacos 中 Seata 名称\n      application: seata-server\n  # 事务组（被一个集群管理）\n  tx-service-group: seata_tx_group\n  service:\n    vgroup-mapping:\n      # 映射到默认的服务分组-名字同上\n      seata_tx_group: default\n  # 实现 AT 模式\n  data-source-proxy-mode: AT\n','472c5c6f0e78fb654abfc43f619e132b','2025-12-15 09:45:29','2026-01-12 06:16:17','fyy','192.168.65.1','dictionary','7','mysql链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(15,'member-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n# 鉴权\nauthorization:\n  # aes 密钥\n  aes-key: authorization-fyy\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','bc83b3fe68ee39310ac8554409d8f7b6','2025-12-15 09:45:29','2025-12-15 13:14:51','fyy','192.168.65.1','member','7','用户中心基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(16,'member-data-test.yaml','service','spring:\n  # JPA设置\n  jpa:\n    hibernate:\n      # 运行时对表得操作\n      # create 启动时删数据库中的表，然后创建，退出时不删除数据表\n      # create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错\n      # update 如果启动时表格式不一致则更新表，原有数据保留\n      # validate 项目启动表结构进行校验 如果不一致则报错\n      ddl-auto: update\n    # sql执行是否打印\n    show-sql: true\n    # 设置参数\n    properties:\n      hibernate:\n        # 格式化sql\n        format_sql: fales\n    # 打开视图\n    open-in-view: true\n    # 数据层目录\n    repository-path: \'cn.fyy.member.repository\'\n    # 数据模型目录\n    po-path: \'cn.fyy.member.bean.po\'\n\n  # 数据源配置\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 拦截器\n      web-stat-filter:\n        # 是否启用\n        enabled: true\n        # 经常需要排除一些不必要的url，比如*.js,/jslib/*等等\n        exclusions: \'*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\'\n        # 配置profileEnable能够监控单个url调用的sql列表\n        profile-enable: true\n        # 关闭session统计功能\n        session-stat-enable: false\n        # 拦截地址\n        url-pattern: /*\n      # 统计配置\n      stat-view-servlet:\n        # 是否启用\n        enabled: true\n        # 统计页面登录账号\n        login-password: fyy\n        # 统计页面登录密码\n        login-username: fyyain77\n        # 是否允许清空统计数据\n        reset-enable: false\n        # 访问地址\n        url-pattern: /druid/*\n        # 允许访问IP\n        allow:\n        # 拒绝访问IP\n        deny:\n      # AOP切面\n      aop-patterns: \'cn.fyy.member.service.impl.*,cn.fyy.member.repository.*\'\n      # 用户数据库读数据源\n      read:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://localhost:3306/member?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 读取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: false\n              # 是否允许执行UPDATE语句\n              update-allow: false\n              # 是否允许执行INSERT语句\n              insert-allow: false\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: false\n              # 是否允许使用SET语法\n              set-allow: false\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: false\n              # 是否允许执行Alter Table语句\n              alter-table-allow: false\n              # 是否允许修改表\n              drop-table-allow: false\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: false\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: false\n              # 是否允许执行roll back操作\n              rollback-allow: false\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n      # 用户数据库写数据源\n      write:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://127.0.0.1:3306/member?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 写取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: true\n              # 是否允许执行UPDATE语句\n              update-allow: true\n              # 是否允许执行INSERT语句\n              insert-allow: true\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: true\n              # 是否允许使用SET语法\n              set-allow: true\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: true\n              # 是否允许执行Alter Table语句\n              alter-table-allow: true\n              # 是否允许修改表\n              drop-table-allow: true\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: true\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: true\n              # 是否允许执行roll back操作\n              rollback-allow: true\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n# 雪花算法配置\nsnowflake-id:\n  # 工作实例ID (0~1023)\n  worker-id: 100\n  # 数据中心ID (0~15)\n  data-center-id: 1\n  # 时间起始时间 (2026-01-01 00:00:00.000)\n  epoch: 1767196800000\n  # 时间倒退策略 wait 或 exception ignore\n  clock-backward-strategy: wait\n\n# 分布式事务\nseata:\n  application-id: seata-${spring.application.name}-${spring.profiles.active}\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.discovery.server-addr}\n      username: ${spring.cloud.nacos.discovery.username}\n      password: ${spring.cloud.nacos.discovery.password}\n      namespace: ${spring.cloud.nacos.discovery.namespace}\n      cluster-name: ${spring.cloud.nacos.discovery.cluster-name}\n      # 分组\n      group: system\n      # Nacos 中 Seata 名称\n      application: seata-server\n  # 事务组（被一个集群管理）\n  tx-service-group: seata_tx_group\n  service:\n    vgroup-mapping:\n      # 映射到默认的服务分组-名字同上\n      seata_tx_group: default\n  # 实现 AT 模式\n  data-source-proxy-mode: AT\n','97ddb7d86a6e5ed26b1953bef02e6a78','2025-12-15 09:45:29','2026-01-12 06:16:47','fyy','192.168.65.1','member','7','mysql链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(17,'message-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','dc43a80093ec5e3d9fdeeee09f9a2f9f','2025-12-15 09:45:29','2025-12-15 13:15:04','fyy','192.168.65.1','message','7','消息中心基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(18,'message-data-test.yaml','service','spring:\n  # JPA设置\n  jpa:\n    hibernate:\n      # 运行时对表得操作\n      # create 启动时删数据库中的表，然后创建，退出时不删除数据表\n      # create-drop 启动时删数据库中的表，然后创建，退出时删除数据表 如果表不存在报错\n      # update 如果启动时表格式不一致则更新表，原有数据保留\n      # validate 项目启动表结构进行校验 如果不一致则报错\n      ddl-auto: update\n    # sql执行是否打印\n    show-sql: true\n    # 设置参数\n    properties:\n      hibernate:\n        # 格式化sql\n        format_sql: fales\n    # 打开视图\n    open-in-view: true\n    # 数据层目录\n    repository-path: \'cn.fyy.message.repository\'\n    # 数据模型目录\n    po-path: \'cn.fyy.message.bean.po\'\n\n  # 数据源配置\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 拦截器\n      web-stat-filter:\n        # 是否启用\n        enabled: true\n        # 经常需要排除一些不必要的url，比如*.js,/jslib/*等等\n        exclusions: \'*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*\'\n        # 配置profileEnable能够监控单个url调用的sql列表\n        profile-enable: true\n        # 关闭session统计功能\n        session-stat-enable: false\n        # 拦截地址\n        url-pattern: /*\n      # 统计配置\n      stat-view-servlet:\n        # 是否启用\n        enabled: true\n        # 统计页面登录账号\n        login-password: fyy\n        # 统计页面登录密码\n        login-username: fyyain77\n        # 是否允许清空统计数据\n        reset-enable: false\n        # 访问地址\n        url-pattern: /druid/*\n        # 允许访问IP\n        allow:\n        # 拒绝访问IP\n        deny:\n      # AOP切面\n      aop-patterns: \'cn.fyy.message.service.impl.*,cn.fyy.message.repository.*\'\n      # 用户数据库读数据源\n      read:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://localhost:3306/message?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 读取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: false\n              # 是否允许执行UPDATE语句\n              update-allow: false\n              # 是否允许执行INSERT语句\n              insert-allow: false\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: false\n              # 是否允许使用SET语法\n              set-allow: false\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: false\n              # 是否允许执行Alter Table语句\n              alter-table-allow: false\n              # 是否允许修改表\n              drop-table-allow: false\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: false\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: false\n              # 是否允许执行roll back操作\n              rollback-allow: false\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n      # 用户数据库写数据源\n      write:\n        # 连接驱动\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        # 数据库地址\n        url: jdbc:mysql://127.0.0.1:3306/message?useUnicode=true&useSSL=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n        # 数据库账号\n        username: root\n        # 数据库密码\n        password: fyyain77\n        # 初始化连接大小\n        initial-size: 10\n        # 连接最小数\n        min-idle: 10\n        # 连接最大数\n        max-active: 100\n        # 获取连接等待超时的时间\n        max-wait: 60000\n        # 启用连接池资源释放\n        keep-alive: true\n        # 用来检测连接是否有效的sql，要求是一个查询语句，常用select \'x\'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。\n        validation-query: select \'x\'\n        # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-borrow: false\n        # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。\n        test-on-return: false\n        # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。\n        test-while-idle: true\n        # 有两个含义：\n        #1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。\n        #2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明\n        time-between-eviction-runs-millis: 60000\n        # 连接保持空闲而不被驱逐的最小时间\n        min-evictable-idle-time-millis: 300000\n        # 连接泄漏监测 在内置监控页面weburi-detail.html中，查看JdbcPoolConnectionOpenCount和JdbcPoolConnectionCloseCount属性，如果不相等，就是泄漏了。\n        remove-abandoned: false\n        remove-abandoned-timeout: 1800\n        log-abandoned: true\n        # 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：\n        # 监控统计用的filter:stat\n        # 日志用的filter:log4j\n        # 防御sql注入的filter:wall\n        filters: stat,slf4j,wall\n        # 细致化配置\n        # 写取拦截sql配置\n        filter:\n          stat:\n            # 是否记录慢SQL\n            log-slow-sql: true\n            # 记录多慢的SQL，毫秒\n            slow-sql-millis: 3000\n            # 合并SQL\n            merge-sql: true\n          wall:\n            # 是否启用\n            enabled: true\n            # 数据库类型\n            db-type: mysql\n            # 对被认为是攻击的SQL进行LOG.error输出\n            log-violation: true\n            # 对被认为是攻击的SQL抛出SQLExcepton\n            throw-exception: true\n            # 拦截配置\n            config:\n              # 拦截语句\n              # 是否允许执行SELECT语句\n              selelct-allow: true\n              # 是否允许执行SELECT * FROM T这样的语句。如果设置为false，不允许执行select * from t，但select * from (select id, name from t) a。这个选项是防御程序通过调用select *获得数据表的结构信息。\n              select-all-column-allow: true\n              # SELECT查询中是否允许INTO字句\n              select-into-allow: false\n              # 是否允许执行DELETE语句\n              delete-allow: true\n              # 是否允许执行UPDATE语句\n              update-allow: true\n              # 是否允许执行INSERT语句\n              insert-allow: true\n              # 是否允许执行REPLACE语句\n              replace-allow: true\n              # 是否允许通过jdbc的call语法调用存储过程\n              call-allow: true\n              # 是否允许使用SET语法\n              set-allow: true\n              # truncate语句是危险(删除所有数据无法回滚)，缺省打开，若需要自行关闭\n              truncate-allow: false\n              # 是否允许创建表\n              create-table-allow: true\n              # 是否允许执行Alter Table语句\n              alter-table-allow: true\n              # 是否允许修改表\n              drop-table-allow: true\n              # 是否允许语句中存在注释，Oracle的用户不用担心，Wall能够识别hints和注释的区别\n              comment-allow: false\n              # 是否允许非以上基本语句的其他语句，缺省关闭，通过这个选项就能够屏蔽DDL。\n              none-base-statement-allow: false\n              # 是否允许一次执行多条语句，缺省关闭\n              multi-statement-allow: true\n              # 是否允许执行mysql的use语句，缺省打开\n              use-allow: false\n              # 是否允许执行mysql的describe语句，缺省打开\n              describe-allow: false\n              # 是否允许执行mysql的show语句，缺省打开\n              show-allow: true\n              # 是否允许执行commit操作\n              commit-allow: true\n              # 是否允许执行roll back操作\n              rollback-allow: true\n              # 永真条件\n              # 检查SELECT语句的WHERE子句是否是一个永真条件\n              select-where-alway-true-check: true\n              # 检查SELECT语句的HAVING子句是否是一个永真条件\n              select-having-alway-true-check: true\n              # 检查DELETE语句的WHERE子句是否是一个永真条件\n              delete-where-alway-true-check: true\n              # 检查DELETE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              delete-where-none-check: true\n              # 检查UPDATE语句的WHERE子句是否是一个永真条件\n              update-where-alay-true-check: true\n              # 检查UPDATE语句是否无where条件，这是有风险的，但不是SQL注入类型的风险\n              update-where-none-check: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永真条件\n              condition-and-alway-true-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含AND永假条件\n              condition-and-alway-false-allow: true\n              # 检查查询条件(WHERE/HAVING子句)中是否包含LIKE永真条件\n              condition-like-true-allow: true\n              # 其他配置\n              # SELECT ... INTO OUTFILE 是否允许，这个是mysql注入攻击的常见手段，缺省是禁止的\n              select-into-outfile-allow: false\n              # 检测SELECT UNION\n              select-union-check: true\n              # 检测SELECT MINUS\n              select-minus-check: true\n              # 检测SELECT EXCEPT\n              select-except-check: true\n              # 检测SELECT INTERSECT\n              select-intersect-check: true\n              # 是否必须参数化，如果为True，则不允许类似WHERE ID = 1这种不参数化的SQL\n              must-parameterized: false\n              # 是否进行严格的语法检测，Druid SQL Parser在某些场景不能覆盖所有的SQL语法，出现解析SQL出错，可以临时把这个选项设置为false，同时把SQL反馈给Druid的开发者。\n              strict-syntax-check: true\n              # 查询条件中是否允许有XOR条件。XOR不常用，很难判断永真或者永假，缺省不允许。\n              condition-op-xor-allow: false\n              # 查询条件中是否允许有\"&\"、\"~\"、\"|\"、\"^\"运算符。\n              condition-op-bitwse-allow: true\n              # 查询条件中是否允许连续两个常量运算表达式\n              condition-double-const-allow: true\n              # 是否允许SELECT * FROM A MINUS SELECT * FROM B这样的语句\n              minus-allow: true\n              # 是否允许SELECT * FROM A INTERSECT SELECT * FROM B这样的语句\n              intersect-allow: true\n              # 拦截常量运算的条件，比如说WHERE FID = 3 - 1，其中\"3 - 1\"是常量运算表达式。\n              const-arithmetic-allow: true\n              # 是否允许limit 0这样的语句\n              limit-zero-allow: false\n              # 配置最大返回行数，如果select语句没有指定最大返回行数，会自动修改selct添加返回限制\n              select-limit: -1\n              # 禁用对象检测配置\n              # 检测是否使用了禁用的表\n              table-check: true\n              # 检测是否使用了禁用的Schema\n              schema-check: true\n              # 检测是否使用了禁用的函数\n              function-check: true\n              # 检测是否使用了“禁用对对象”\n              object-check: true\n              # 检测是否使用了“禁用的变量”\n              variant-check: true\n              # 指定的表只读，不能够在SELECT INTO、DELETE、UPDATE、INSERT、MERGE中作为\"被修改表\"出现<\n              read-only-tables:\n              # JDBC相关配置\n              # 是否允许调用Connection.getMetadata方法，这个方法调用会暴露数据库的表信息  false会导致报错但不影响功能使用\n              metadata-allow: true\n              # 是否允许调用Connection/Statement/ResultSet的isWrapFor和unwrap方法，这两个方法调用，使得有办法拿到原生驱动的对象，绕过WallFilter的检测直接执行SQL。\n              wrap-allow: false\n\n# 雪花算法配置\nsnowflake-id:\n  # 工作实例ID (0~1023)\n  worker-id: 600\n  # 数据中心ID (0~15)\n  data-center-id: 1\n  # 时间起始时间 (2026-01-01 00:00:00.000)\n  epoch: 1767196800000\n  # 时间倒退策略 wait 或 exception ignore\n  clock-backward-strategy: wait\n\n# 分布式事务\nseata:\n  application-id: seata-${spring.application.name}-${spring.profiles.active}\n  registry:\n    type: nacos\n    nacos:\n      server-addr: ${spring.cloud.nacos.discovery.server-addr}\n      username: ${spring.cloud.nacos.discovery.username}\n      password: ${spring.cloud.nacos.discovery.password}\n      namespace: ${spring.cloud.nacos.discovery.namespace}\n      cluster-name: ${spring.cloud.nacos.discovery.cluster-name}\n      # 分组\n      group: system\n      # Nacos 中 Seata 名称\n      application: seata-server\n  # 事务组（被一个集群管理）\n  tx-service-group: seata_tx_group\n  service:\n    vgroup-mapping:\n      # 映射到默认的服务分组-名字同上\n      seata_tx_group: default\n  # 实现 AT 模式\n  data-source-proxy-mode: AT\n','74dc323e0b602ecf5cc6889c8a31935c','2025-12-15 09:45:29','2026-01-12 06:17:07','fyy','192.168.65.1','message','7','mysql链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(19,'rocketmq-test.yaml','system','spring:\n  cloud:\n    # 两个 bindings 这种设计是为了分离 行为配置 和 主题绑定配置，使得配置更加清晰和灵活。\n    stream:\n      rocketmq:\n        binder:\n          # RocketMQ NameServer 地址\n          name-server: 127.0.0.1:9876\n        # 定义生产者和消费者的高级行为，-out-0是必须的，-out或-in是生产者或消费者，-0是一个标识\n        bindings:\n          # 生产者\n          share-out-0:\n            producer:\n              # 生产者分组\n              group: share_producer\n          # 消费者\n          share-in-0:\n            consumer:\n              # 消费者消费模式-集群模式\n              messageModel: CLUSTERING\n      # 定义消息通道与 RocketMQ 主题的绑定关系，需要与上方一致\n      bindings:\n        # 生产者\n        share-out-0:\n          # 主题\n          destination: share\n        # 消费者\n        share-in-0:\n          # 主题\n          destination: share\n          # 消费者分组\n          group: share_consumer\n','850f23027398b60ecce44cadc8736be0','2025-12-15 09:45:29','2025-12-15 09:45:29','fyy','192.168.65.1','rocketmq','7','rocketmq链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(20,'mongodb-test.yaml','system','spring:\n  # mongodb 配置\n  data:\n    mongodb:\n      host: 127.0.0.1\n      port: 17017\n      username: fyy\n      password: fyyain77\n      database: test','1f096c1b8207b7eeddfef2ec3d112496','2025-12-15 09:45:30','2025-12-15 09:45:30','fyy','192.168.65.1','mongodb','7','rocketmq链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(21,'elasticsearch-test.yaml','system','spring:\n  # elasticsearch 配置\n  elasticsearch:\n    rest:\n      # 根据实际情况修改地址和端口\n      uris: http://127.0.0.1:9200\n      # 用户名\n      username: elastic\n      # 密码\n      password: fyyain77','bc74e63918fd8228d50bc78639392ea6','2025-12-15 09:45:30','2025-12-15 09:45:30','fyy','192.168.65.1','elasticsearch','7','elasticsearch链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(22,'seata-server-test.yaml','system','# seata 在docker运行，配置在这是因为seata配置了来nacos获取\nseata:\n  # 事务存储配置，仅用于服务器。file、db和redis的配置值是可选的。\n  store:\n    mode: db\n    lock:\n      mode: db\n    session:\n      mode: db\n    # 用于密码加密\n    public-key:\n\n    # 如果“存储模式”为“db”，则需要这些配置。如果“store.mode, store.lock.mode store.session。Mode ‘不等于’ db \'，您可以删除配置块。\n    db:\n      datasource: druid\n      # 数据库类型\n      db-type: mysql\n      # 连接驱动\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      # 数据库地址\n      url: jdbc:mysql://host.docker.internal:3306/seata?useUnicode=true&rewriteBatchedStatements=true\n      # 数据库账号\n      user: root\n      # 数据库密码\n      password: fyyain77\n      min-conn: 10\n      max-conn: 100\n      # 全局事务表\n      global-table: global_table\n      # 分支事务表\n      branch-table: branch_table\n      # 全局事务锁表\n      lock-table: lock_table\n      # 分支事务锁表（多 Server 集群下保证同时只有一个 Server 处理提交或回滚）\n      distributed-lock-table: distributed_lock\n      # v组表\n      vgroup-table: vgroup_table\n      query-limit: 1000\n      max-wait: 5000\n      druid:\n        time-between-eviction-runs-millis: 120000\n        min-evictable-idle-time-millis: 300000\n        test-while-idle: true\n        test-on-borrow: false\n        keep-alive: false\n        filter:\n          wall:\n            config:\n              delete-allow: true\n      hikari:\n        idle-timeout: 600000\n        keepalive-time: 120000\n        max-lifetime: 1800000\n        validation-timeout: 5000\n      dbcp:\n        time-between-eviction-runs-millis: 120000\n        min-evictable-idle-time-millis: 300000\n        test-while-idle: true\n        test-on-borrow: false','327bf5318b9d053915de45ebe66699d9','2025-12-15 09:45:30','2025-12-24 02:28:28','fyy','192.168.65.1','seata','7','seata服务配置-测试环境',NULL,NULL,'yaml',NULL,''),(23,'job-base-test.yaml','service','spring:\n  jackson:\n    # 时间校准\n    time-zone: Asia/Shanghai\n    # 时间格式化\n    date-format: yyyy-MM-dd HH:mm:ss\n  thymeleaf:\n    # 不缓存前端\n    cache: false\n# 健康检查相关配置\nmanagement:\n  # 暴露内容配置\n  endpoints:\n    # web端点配置\n    web:\n      # 暴露内容配置\n      exposure:\n        # 确保健康检查端点被公开\n        include: \'health\'\n  # 返回内容配置\n  endpoint:\n    # 健康检查\n    health:\n      # 确保健康检查返回详细信息\n      show-details: always\n# 鉴权\nauthorization:\n  # aes 密钥\n  aes-key: authorization-fyy\n\n# Swagger 开启/关闭，测试环境可以开启，生产环境关闭\nspringdoc:\n  api-docs:\n    enabled: false\n  swagger-ui:\n    enabled: false','bc83b3fe68ee39310ac8554409d8f7b6','2025-12-15 09:45:30','2026-02-13 06:51:12','fyy','142.250.196.219','job','7','定时任务执行器基础配置-测试环境',NULL,NULL,'yaml',NULL,''),(24,'job-xxl-test.yaml','service','xxl:\n  job:\n    admin:\n      # 调度中心部署根地址 [必填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行\"执行器心跳注册\"和\"任务结果回调\"；为空则关闭自动注册；\n      addresses: http://127.0.0.1:10905\n      # 调度中心通讯TOKEN [选填]：非空时启用；\n      accessToken: build\n      # 调度中心通讯超时时间[选填]，单位秒；默认3s；\n      timeout: 3\n    executor:\n      # 执行器AppName [必填]：执行器心跳注册分组依据；为空则关闭自动注册\n      appname: build-xxl-job-executor\n      # 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 \"IP:PORT\" 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。\n      address:\n      # 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯使用；地址信息用于 \"执行器注册\" 和 \"调度中心请求并触发任务\"；\n      ip:\n      # 执行器端口号 [必填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；\n      port: 27999\n      # 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；\n      logpath: log/${spring.application.name}-handler-${spring.profiles.active}\n      # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；\n      logretentiondays: 30','df2a98552abd127517874f3f09c5501f','2025-12-15 09:45:30','2026-07-22 08:31:02','fyy','192.168.65.1','job','7','定时任务执行器xxl链接配置-测试环境',NULL,NULL,'yaml',NULL,''),(25,'rustfs-test.yaml','system','# 文件系统-来源于项目resources下的credentials.json\nrustfs:\n  # okhttp3 不能直接使用127.0.0.1所以使用localhost\n  domain: localhost\n  port: 10000\n  access-key: IPBwkfeL3HnUCoqrlZWa\n  secret-key: GRrD0A46f1zFMpkQundT8eWYBxsSijHcJyLEtoOU\n  bucket-name: build','b9a33aebf31e5e21691ad59e336186d4','2025-12-15 09:48:02','2025-12-17 15:42:17','fyy','192.168.65.1','rustfs','7','rustfs链接配置-测试环境',NULL,NULL,'yaml',NULL,'');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_gray`
--

DROP TABLE IF EXISTS `config_info_gray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_gray` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `src_user` text COMMENT 'src_user',
  `src_ip` varchar(100) DEFAULT NULL COMMENT 'src_ip',
  `gmt_create` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT 'gmt_create',
  `gmt_modified` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT 'gmt_modified',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `gray_name` varchar(128) NOT NULL COMMENT 'gray_name',
  `gray_rule` text NOT NULL COMMENT 'gray_rule',
  `encrypted_data_key` varchar(256) NOT NULL DEFAULT '' COMMENT 'encrypted_data_key',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfogray_datagrouptenantgray` (`data_id`,`group_id`,`tenant_id`,`gray_name`),
  KEY `idx_dataid_gmt_modified` (`data_id`,`gmt_modified`),
  KEY `idx_gmt_modified` (`gmt_modified`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='config_info_gray';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_gray`
--

LOCK TABLES `config_info_gray` WRITE;
/*!40000 ALTER TABLE `config_info_gray` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_gray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增长标识',
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
INSERT INTO `group_capacity` (`id`, `group_id`, `quota`, `usage`, `max_size`, `max_aggr_count`, `max_aggr_size`, `max_history_count`, `gmt_create`, `gmt_modified`) VALUES (1,'',0,24,0,0,0,0,'2025-12-15 17:48:02','2026-07-22 19:48:37');
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL COMMENT 'id',
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增标识',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `op_type` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'operation type',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '密钥',
  `publish_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT 'formal' COMMENT 'publish type gray or formal',
  `gray_name` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'gray name',
  `ext_info` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'ext info',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`, `encrypted_data_key`, `publish_type`, `gray_name`, `ext_info`) VALUES (24,104,'job-xxl-test.yaml','service','job','xxl:\n  job:\n    admin:\n      # 调度中心部署根地址 [必填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行\"执行器心跳注册\"和\"任务结果回调\"；为空则关闭自动注册；\n      addresses: http://127.0.0.1:10905/xxl-job-admin\n      # 调度中心通讯TOKEN [选填]：非空时启用；\n      accessToken: fyy\n      # 调度中心通讯超时时间[选填]，单位秒；默认3s；\n      timeout: 3\n    executor:\n      # 执行器AppName [必填]：执行器心跳注册分组依据；为空则关闭自动注册\n      appname: build-xxl-job-executor\n      # 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 \"IP:PORT\" 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。\n      address:\n      # 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯使用；地址信息用于 \"执行器注册\" 和 \"调度中心请求并触发任务\"；\n      ip:\n      # 执行器端口号 [必填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；\n      port: 27999\n      # 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；\n      logpath: log/${spring.application.name}-handler-${spring.profiles.active}\n      # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；\n      logretentiondays: 30','6069cc039204971fe79a4dbc1b487589','2026-07-20 15:20:48','2026-07-20 23:20:49','fyy','192.168.65.1','U','7','','formal','','{\"type\":\"yaml\",\"src_user\":\"fyy\",\"c_desc\":\"定时任务执行器xxl链接配置-测试环境\"}'),(4,105,'mail-test.yaml','system','mail','#邮箱配置\nspring:\n  mail:\n    host: smtp.sina.cn\n    username: fuyongyuan277@sina.cn\n    password: d0afada3105a3a39\n    properties:\n      personal: \'哎呋歪歪平台\'','2d30a91297693c0d33cb1efe9dc45c82','2026-07-20 15:28:44','2026-07-20 23:28:44','fyy','192.168.65.1','U','7','','formal','','{\"type\":\"yaml\",\"src_user\":\"fyy\",\"c_desc\":\"mail链接配置-测试环境\"}'),(24,106,'job-xxl-test.yaml','service','job','xxl:\n  job:\n    admin:\n      # 调度中心部署根地址 [必填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行\"执行器心跳注册\"和\"任务结果回调\"；为空则关闭自动注册；\n      addresses: http://127.0.0.1:10905\n      # 调度中心通讯TOKEN [选填]：非空时启用；\n      accessToken: fyy\n      # 调度中心通讯超时时间[选填]，单位秒；默认3s；\n      timeout: 3\n    executor:\n      # 执行器AppName [必填]：执行器心跳注册分组依据；为空则关闭自动注册\n      appname: build-xxl-job-executor\n      # 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 \"IP:PORT\" 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。\n      address:\n      # 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯使用；地址信息用于 \"执行器注册\" 和 \"调度中心请求并触发任务\"；\n      ip:\n      # 执行器端口号 [必填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；\n      port: 27999\n      # 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；\n      logpath: log/${spring.application.name}-handler-${spring.profiles.active}\n      # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；\n      logretentiondays: 30','aa230561384bb924b902414496a7985e','2026-07-22 08:26:32','2026-07-22 16:26:33','fyy','192.168.65.1','U','7','','formal','','{\"type\":\"yaml\",\"src_user\":\"fyy\",\"c_desc\":\"定时任务执行器xxl链接配置-测试环境\"}'),(24,107,'job-xxl-test.yaml','service','job','xxl:\n  job:\n    admin:\n      # 调度中心部署根地址 [必填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行\"执行器心跳注册\"和\"任务结果回调\"；为空则关闭自动注册；\n      addresses: http://127.0.0.1:10905\n      # 调度中心通讯超时时间[选填]，单位秒；默认3s；\n      timeout: 3\n    executor:\n      # 执行器AppName [必填]：执行器心跳注册分组依据；为空则关闭自动注册\n      appname: build-xxl-job-executor\n      # 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 \"IP:PORT\" 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。\n      address:\n      # 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯使用；地址信息用于 \"执行器注册\" 和 \"调度中心请求并触发任务\"；\n      ip:\n      # 执行器端口号 [必填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；\n      port: 27999\n      # 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；\n      logpath: log/${spring.application.name}-handler-${spring.profiles.active}\n      # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；\n      logretentiondays: 30\n      # 调度中心通讯TOKEN [必填]：非空时启用；\n      accessToken: build','f12989ffc99981066f9288815c18657a','2026-07-22 08:28:21','2026-07-22 16:28:21','fyy','192.168.65.1','U','7','','formal','','{\"type\":\"yaml\",\"src_user\":\"fyy\",\"c_desc\":\"定时任务执行器xxl链接配置-测试环境\"}'),(24,108,'job-xxl-test.yaml','service','job','xxl:\n  job:\n    admin:\n      # 调度中心部署根地址 [必填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行\"执行器心跳注册\"和\"任务结果回调\"；为空则关闭自动注册；\n      addresses: http://127.0.0.1:10905\n      # 调度中心通讯TOKEN [必填]：非空时启用；\n      accessToken: build\n      # 调度中心通讯超时时间[选填]，单位秒；默认3s；\n      timeout: 3\n    executor:\n      # 执行器AppName [必填]：执行器心跳注册分组依据；为空则关闭自动注册\n      appname: build-xxl-job-executor\n      # 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 \"IP:PORT\" 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。\n      address:\n      # 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯使用；地址信息用于 \"执行器注册\" 和 \"调度中心请求并触发任务\"；\n      ip:\n      # 执行器端口号 [必填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；\n      port: 27999\n      # 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；\n      logpath: log/${spring.application.name}-handler-${spring.profiles.active}\n      # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；\n      logretentiondays: 30','410e9eb5217d270fa3a179a8a8230971','2026-07-22 08:30:03','2026-07-22 16:30:04','fyy','192.168.65.1','U','7','','formal','','{\"type\":\"yaml\",\"src_user\":\"fyy\",\"c_desc\":\"定时任务执行器xxl链接配置-测试环境\"}'),(24,109,'job-xxl-test.yaml','service','job','xxl:\n  job:\n    admin:\n      # 调度中心部署根地址 [必填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行\"执行器心跳注册\"和\"任务结果回调\"；为空则关闭自动注册；\n      addresses: http://127.0.0.1:10905\n      # 调度中心通讯TOKEN [选填]：非空时启用；\n      accessToken: fyy\n      # 调度中心通讯超时时间[选填]，单位秒；默认3s；\n      timeout: 3\n    executor:\n      # 执行器AppName [必填]：执行器心跳注册分组依据；为空则关闭自动注册\n      appname: build-xxl-job-executor\n      # 调度中心通讯TOKEN [必填]：非空时启用；\n      accessToken: build\n      # 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 \"IP:PORT\" 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。\n      address:\n      # 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯使用；地址信息用于 \"执行器注册\" 和 \"调度中心请求并触发任务\"；\n      ip:\n      # 执行器端口号 [必填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；\n      port: 27999\n      # 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；\n      logpath: log/${spring.application.name}-handler-${spring.profiles.active}\n      # 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；\n      logretentiondays: 30','162c56a7ef0040e95a5ae24d7c65759d','2026-07-22 08:31:02','2026-07-22 16:31:02','fyy','192.168.65.1','U','7','','formal','','{\"type\":\"yaml\",\"src_user\":\"fyy\",\"c_desc\":\"定时任务执行器xxl链接配置-测试环境\"}');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'role',
  `resource` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'resource',
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'action',
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'username',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'role',
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`username`, `role`) VALUES ('fyy','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
INSERT INTO `tenant_capacity` (`id`, `tenant_id`, `quota`, `usage`, `max_size`, `max_aggr_count`, `max_aggr_size`, `max_history_count`, `gmt_create`, `gmt_modified`) VALUES (1,'7',0,24,0,0,0,0,'2025-12-15 17:48:02','2026-07-22 19:48:37');
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` (`id`, `kp`, `tenant_id`, `tenant_name`, `tenant_desc`, `create_source`, `gmt_create`, `gmt_modified`) VALUES (1,'1','7','build','哎呋歪歪集群-快速构建集群','nacos',1765791914562,1765791914562);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'username',
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'password',
  `enabled` tinyint(1) NOT NULL COMMENT 'enabled',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('fyy','$2a$10$SoH.J4eEyWqkgJ21BAOUCeAQPz9451CtxfcaKTrZ0DIgBzRzui23a',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `seata`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `seata` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `seata`;

--
-- Table structure for table `branch_table`
--

DROP TABLE IF EXISTS `branch_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_table` (
  `branch_id` bigint NOT NULL COMMENT '分支事务ID',
  `xid` varchar(128) NOT NULL COMMENT '全局事务ID',
  `transaction_id` bigint DEFAULT NULL COMMENT '事务ID',
  `resource_group_id` varchar(32) DEFAULT NULL COMMENT '资源组ID',
  `resource_id` varchar(256) DEFAULT NULL COMMENT '资源ID',
  `branch_type` varchar(8) DEFAULT NULL COMMENT '分支类型',
  `status` tinyint DEFAULT NULL COMMENT '状态',
  `client_id` varchar(64) DEFAULT NULL COMMENT '客户端ID',
  `application_data` varchar(2000) DEFAULT NULL COMMENT '应用扩展数据',
  `gmt_create` datetime(6) DEFAULT NULL COMMENT '记录创建时间',
  `gmt_modified` datetime(6) DEFAULT NULL COMMENT '记录最后修改时间',
  PRIMARY KEY (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分支事务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_table`
--

LOCK TABLES `branch_table` WRITE;
/*!40000 ALTER TABLE `branch_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributed_lock`
--

DROP TABLE IF EXISTS `distributed_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distributed_lock` (
  `lock_key` char(20) NOT NULL COMMENT '锁键',
  `lock_value` varchar(20) NOT NULL COMMENT '锁值',
  `expire` bigint DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`lock_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分布式锁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributed_lock`
--

LOCK TABLES `distributed_lock` WRITE;
/*!40000 ALTER TABLE `distributed_lock` DISABLE KEYS */;
INSERT INTO `distributed_lock` (`lock_key`, `lock_value`, `expire`) VALUES ('AsyncCommitting',' ',0),('RetryCommitting',' ',0),('RetryRollbacking',' ',0),('TxTimeoutCheck',' ',0);
/*!40000 ALTER TABLE `distributed_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global_table`
--

DROP TABLE IF EXISTS `global_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_table` (
  `xid` varchar(128) NOT NULL COMMENT '全局事务ID',
  `transaction_id` bigint DEFAULT NULL COMMENT '事务ID',
  `status` tinyint NOT NULL COMMENT '状态',
  `application_id` varchar(32) DEFAULT NULL COMMENT '应用ID',
  `transaction_service_group` varchar(32) DEFAULT NULL COMMENT '事务服务组名',
  `transaction_name` varchar(128) DEFAULT NULL COMMENT '事务名称',
  `timeout` int DEFAULT NULL COMMENT '超时时间',
  `begin_time` bigint DEFAULT NULL COMMENT '事务开始时间',
  `application_data` varchar(2000) DEFAULT NULL COMMENT '应用扩展数据',
  `gmt_create` datetime DEFAULT NULL COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '记录最后修改时间',
  PRIMARY KEY (`xid`),
  KEY `idx_status_gmt_modified` (`status`,`gmt_modified`),
  KEY `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='全局事务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global_table`
--

LOCK TABLES `global_table` WRITE;
/*!40000 ALTER TABLE `global_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `global_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lock_table`
--

DROP TABLE IF EXISTS `lock_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lock_table` (
  `row_key` varchar(128) NOT NULL COMMENT '行锁键',
  `xid` varchar(128) DEFAULT NULL COMMENT '全局事务ID',
  `transaction_id` bigint DEFAULT NULL COMMENT '事务ID',
  `branch_id` bigint NOT NULL COMMENT '分支事务ID',
  `resource_id` varchar(256) DEFAULT NULL COMMENT '资源ID',
  `table_name` varchar(32) DEFAULT NULL COMMENT '表名',
  `pk` varchar(36) DEFAULT NULL COMMENT '主键值',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态 0:locked ,1:rollbacking',
  `gmt_create` datetime DEFAULT NULL COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '记录最后修改时间',
  PRIMARY KEY (`row_key`),
  KEY `idx_status` (`status`),
  KEY `idx_branch_id` (`branch_id`),
  KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='锁记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lock_table`
--

LOCK TABLES `lock_table` WRITE;
/*!40000 ALTER TABLE `lock_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `lock_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vgroup_table`
--

DROP TABLE IF EXISTS `vgroup_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vgroup_table` (
  `vGroup` varchar(255) DEFAULT NULL COMMENT 'v组',
  `namespace` varchar(255) DEFAULT NULL COMMENT '命名空间',
  `cluster` varchar(255) DEFAULT NULL COMMENT '集群',
  UNIQUE KEY `idx_vgroup_namespace_cluster` (`vGroup`,`namespace`,`cluster`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='v组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vgroup_table`
--

LOCK TABLES `vgroup_table` WRITE;
/*!40000 ALTER TABLE `vgroup_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `vgroup_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `xxl_job`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `xxl_job` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `xxl_job`;

--
-- Table structure for table `xxl_job_group`
--

DROP TABLE IF EXISTS `xxl_job_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `name` varchar(64) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `access_token` varchar(255) DEFAULT NULL COMMENT '执行器AccessToken',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_app_name` (`app_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_group`
--

LOCK TABLES `xxl_job_group` WRITE;
/*!40000 ALTER TABLE `xxl_job_group` DISABLE KEYS */;
INSERT INTO `xxl_job_group` (`id`, `app_name`, `name`, `address_type`, `address_list`, `access_token`, `update_time`) VALUES (1,'xxl-job-executor-sample','通用执行器Sample',0,NULL,'default_token','2026-07-22 19:57:30'),(2,'xxl-job-executor-sample-ai','AI执行器Sample',0,NULL,'default_token','2026-07-22 19:57:30'),(3,'build-xxl-job-executor','哎呋歪歪集群',0,NULL,'build','2026-07-22 19:57:30');
/*!40000 ALTER TABLE `xxl_job_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_info`
--

DROP TABLE IF EXISTS `xxl_job_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `name` varchar(255) NOT NULL COMMENT '执行器名称',
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '任务handler',
  `executor_param` text COMMENT '任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_info`
--

LOCK TABLES `xxl_job_info` WRITE;
/*!40000 ALTER TABLE `xxl_job_info` DISABLE KEYS */;
INSERT INTO `xxl_job_info` (`id`, `job_group`, `name`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`, `add_time`, `update_time`) VALUES (1,1,'示例任务01','XXL','','CRON','0 0 0 * * ? *','DO_NOTHING','FIRST','demoJobHandler','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2026-07-20 03:30:27','',0,0,0,'2026-07-20 03:30:27','2026-07-20 03:30:27'),(2,2,'Ollama示例任务','XXL','','NONE','','DO_NOTHING','FIRST','ollamaJobHandler','{\n    \"input\": \"Java实现二叉树层序遍历\",\n    \"prompt\": \"你是一个研发工程师，擅长解决技术类问题。\",\n    \"model\": \"qwen3.5:0.8b\"\n}','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2026-07-20 03:30:27','',0,0,0,'2026-07-20 03:30:27','2026-07-20 03:30:27'),(3,2,'Dify示例任务','XXL','','NONE','','DO_NOTHING','FIRST','difyWorkflowJobHandler','{\n    \"inputs\":{\n        \"input\":\"查询班级各学科前三名\"\n    },\n    \"user\": \"xxl-job\",\n    \"baseUrl\": \"http://localhost/v1\",\n    \"apiKey\": \"app-OUVgNUOQRIMokfmuJvBJoUTN\"\n}','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2026-07-20 03:30:27','',0,0,0,'2026-07-20 03:30:27','2026-07-20 03:30:27'),(4,2,'OpenClaw示例任务','XXL','','NONE','','DO_NOTHING','FIRST','openClawJobHandler','{\n    \"input\": \"查看下上海今天得天气，给出出游建议\",\n    \"prompt\": \"你是一个出游助手，擅长做旅游规划\"\n}','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2026-07-20 03:30:27','',0,0,0,'2026-07-20 03:30:27','2026-07-20 03:30:27'),(5,3,'更新Api信息','哎呋歪歪','','CRON','0 * * * * ?','DO_NOTHING','FIRST','ApiSaveJobHandler','','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2026-02-12 10:17:17','',1,1784721420000,1784721480000,'2026-02-12 10:17:17','2026-07-22 16:33:54');
/*!40000 ALTER TABLE `xxl_job_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_lock`
--

DROP TABLE IF EXISTS `xxl_job_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_lock`
--

LOCK TABLES `xxl_job_lock` WRITE;
/*!40000 ALTER TABLE `xxl_job_lock` DISABLE KEYS */;
INSERT INTO `xxl_job_lock` (`lock_name`) VALUES ('schedule_lock');
/*!40000 ALTER TABLE `xxl_job_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_log`
--

DROP TABLE IF EXISTS `xxl_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '任务handler',
  `executor_param` text COMMENT '任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `i_trigger_time` (`trigger_time`),
  KEY `i_handle_code` (`handle_code`),
  KEY `i_job_group` (`job_group`),
  KEY `i_job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1827 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_log`
--

LOCK TABLES `xxl_job_log` WRITE;
/*!40000 ALTER TABLE `xxl_job_log` DISABLE KEYS */;
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (1624,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:35:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:35:00',200,'',0),(1625,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:36:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:36:00',200,'',0),(1626,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:37:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:37:00',200,'',0),(1627,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:38:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:38:00',200,'',0),(1628,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:39:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:39:00',200,'',0),(1629,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:40:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:40:00',200,'',0),(1630,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:41:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:41:00',200,'',0),(1631,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:42:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:42:00',200,'',0),(1632,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:43:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:43:00',200,'',0),(1633,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:44:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:44:00',200,'',0),(1634,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:45:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:45:00',200,'',0),(1635,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:46:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:46:00',200,'',0),(1636,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:47:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:47:00',200,'',0),(1637,3,5,'http://192.168.64.1:27999/','ApiSaveJobHandler','',NULL,0,'2026-07-22 16:48:00',200,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.64.1:27999/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：http://192.168.64.1:27999/<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：success','2026-07-22 16:48:00',500,'java.lang.reflect.InvocationTargetException\n	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:119)\n	at java.base/java.lang.reflect.Method.invoke(Method.java:565)\n	at com.xxl.job.core.handler.impl.MethodJobHandler.$sw$original$execute$22702m2(MethodJobHandler.java:31)\n	at com.xxl.job.core.handler.impl.MethodJobHandler.$sw$original$execute$22702m2$accessor$$sw$9hiudr1(MethodJobHandler.java)\n	at com.xxl.job.core.handler.impl.MethodJobHandler$$sw$auxiliary$2o853k3.call(Unknown Source)\n	at org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstMethodsInter.intercept(InstMethodsInter.java:95)\n	at com.xxl.job.core.handler.impl.MethodJobHandler.execute(MethodJobHandler.java)\n	at com.xxl.job.core.thread.JobThread.run(JobThread.java:161)\nCaused by: feign.FeignException$ServiceUnavailable: [503] during [POST] to [http://capability/api/feign/save/list/3DAC5920991703589AB6DD5FA4FD3BED] [ApiFeignClient#feignSaveList(List,String)]: [Load balancer does not contain an instance for the service capability]\n	at feign.FeignException.serverErrorStatus(FeignException.java:287)\n	at feign.FeignException.errorStatus(FeignException.java:226)\n	at feign.FeignException.errorStatus(FeignException.java:213)\n	at feign.codec.ErrorDecoder$Default.decode(ErrorDecoder.java:103)\n	at feign.InvocationContext.decodeError(InvocationContext.java:133)\n	at feign.InvocationContext.proceed(InvocationContext.java:80)\n	at feign.ResponseHandler.handleResponse(ResponseHandler.java:69)\n	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:109)\n	at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:53)\n	at feign.ReflectiveFeign$FeignInvocationHandler.invoke(ReflectiveFeign.java:104)\n	at org.springframework.cloud.openfeign.FeignCachingInvocationHandlerFactory$1.proceed(FeignCachingInvocationHandlerFactory.java:66)\n	at org.springframework.cache.interceptor.CacheInterceptor.lambda$invoke$0(CacheInterceptor.java:54)\n	at org.springframework.cache.interceptor.CacheAspectSupport.invokeOperation(CacheAspectSupport.java:424)\n	at org.springframework.cache.interceptor.CacheAspectSupport.execute(CacheAspectSupport.java:410)\n	at org.springframework.cache.interceptor.CacheInterceptor.invoke(CacheInterceptor.java:64)\n	at org.springframework.cloud.openfeign.FeignCachingInvocationHandlerFactory.lambda$create$0(FeignCachingInvocationHandlerFactory.java:53)\n	at jdk.proxy2/jdk.proxy2.$Proxy171.feignSaveList(Unknown Source)\n	at cn.fyy.job.service.CapabilityApiJobService.apiSaveJobHandler(CapabilityApiJobService.java:72)\n	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)\n	... 7 more\n',2),(1638,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:49:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1639,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:50:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1640,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:51:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1641,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:52:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1642,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:53:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1643,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:54:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1644,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:55:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1645,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:56:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1646,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:57:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1647,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:58:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1648,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 16:59:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1649,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:00:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1650,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:01:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1651,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:02:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1652,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:03:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1653,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:04:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1654,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:05:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1655,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:06:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1656,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:07:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1657,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:08:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1658,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:09:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1659,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:10:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1660,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:11:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1661,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:12:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1662,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1663,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:14:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1664,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:15:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1665,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:16:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1666,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:17:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1667,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:18:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1668,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:19:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1669,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:20:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1670,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:21:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1671,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:22:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1672,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:23:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1673,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:24:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1674,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:25:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1675,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:26:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1676,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:27:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1677,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:28:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1678,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:29:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1679,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:30:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1680,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:31:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1681,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:32:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1682,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:33:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1683,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:34:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1684,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:35:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1685,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:36:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1686,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:37:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1687,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:38:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1688,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:39:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1689,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:40:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1690,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:41:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1691,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:42:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1692,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:43:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1693,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:44:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1694,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:45:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1695,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:46:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1696,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:47:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1697,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:48:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1698,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:49:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1699,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:50:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1700,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:51:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1701,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:52:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1702,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:53:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1703,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:54:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1704,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:55:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1705,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:56:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1706,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:57:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1707,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:58:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1708,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 17:59:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1709,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:00:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1710,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:01:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1711,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:02:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1712,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:03:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1713,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:04:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1714,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:05:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1715,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:06:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1716,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:07:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1717,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:08:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1718,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:09:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1719,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:10:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1720,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:11:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1721,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:12:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1722,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1723,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:14:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1724,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:15:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1725,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:16:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1726,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:17:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1727,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:18:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1728,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:19:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1729,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:20:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1730,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:21:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1731,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:22:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1732,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:23:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1733,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:24:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1734,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:25:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1735,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:26:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1736,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:27:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1737,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:28:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1738,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:29:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1739,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:30:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1740,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:31:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1741,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:32:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1742,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:33:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1743,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:34:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1744,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:35:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1745,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:36:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1746,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:37:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1747,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:38:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1748,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:39:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1749,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:40:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1750,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:41:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1751,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:42:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1752,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:43:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1753,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:44:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1754,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:45:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1755,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:46:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1756,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:47:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1757,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:48:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1758,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:49:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1759,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:50:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1760,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:51:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1761,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:52:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1762,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:53:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1763,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:54:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1764,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:55:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1765,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:56:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1766,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:57:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1767,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:58:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1768,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 18:59:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1769,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:00:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1770,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:01:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1771,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:02:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1772,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:03:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1773,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:04:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1774,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:05:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1775,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:06:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1776,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:07:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1777,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:08:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1778,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:09:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1779,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:10:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1780,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:11:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1781,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:12:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1782,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:13:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1783,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:14:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1784,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:15:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1785,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:16:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1786,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:17:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1787,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:18:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1788,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:19:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1789,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:20:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1790,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:21:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1791,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:22:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1792,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:23:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1793,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:24:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1794,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:25:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1795,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:26:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1796,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:27:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1797,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:28:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1798,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:29:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1799,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:30:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1800,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:31:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1801,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:32:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1802,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:33:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1803,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:34:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1804,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:35:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1805,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:36:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1806,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:37:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1807,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:38:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1808,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:39:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1809,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:40:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1810,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:41:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1811,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:42:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1812,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:43:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1813,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:44:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1814,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:45:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1815,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:46:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1816,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:47:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1817,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:48:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1818,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:49:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1819,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:50:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1820,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:51:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1821,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:52:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1822,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:53:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1823,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:54:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1824,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:55:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1825,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:56:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2),(1826,3,5,NULL,'ApiSaveJobHandler','',NULL,0,'2026-07-22 19:57:00',500,'任务触发类型：Cron触发<br>调度机器：172.17.0.7<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br><br>执行器地址：address route fail, 调度失败：执行器地址为空<br>JobHandler：ApiSaveJobHandler<br>任务参数：<br>调度备注：error, Address Router Fail.',NULL,0,NULL,2);
/*!40000 ALTER TABLE `xxl_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_log_report`
--

DROP TABLE IF EXISTS `xxl_job_log_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_log_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7921 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_log_report`
--

LOCK TABLES `xxl_job_log_report` WRITE;
/*!40000 ALTER TABLE `xxl_job_log_report` DISABLE KEYS */;
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (1,'2026-07-20 00:00:00',0,0,0,'2026-07-22 19:57:00'),(2,'2026-07-19 00:00:00',0,0,0,'2026-07-21 23:59:00'),(3,'2026-07-18 00:00:00',0,0,0,'2026-07-20 23:59:00'),(2161,'2026-07-21 00:00:00',0,0,0,'2026-07-22 19:57:00'),(4282,'2026-07-22 00:00:00',0,13,189,'2026-07-22 19:57:00');
/*!40000 ALTER TABLE `xxl_job_log_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_logglue`
--

DROP TABLE IF EXISTS `xxl_job_logglue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_logglue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_logglue`
--

LOCK TABLES `xxl_job_logglue` WRITE;
/*!40000 ALTER TABLE `xxl_job_logglue` DISABLE KEYS */;
/*!40000 ALTER TABLE `xxl_job_logglue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_registry`
--

DROP TABLE IF EXISTS `xxl_job_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_registry` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_registry`
--

LOCK TABLES `xxl_job_registry` WRITE;
/*!40000 ALTER TABLE `xxl_job_registry` DISABLE KEYS */;
/*!40000 ALTER TABLE `xxl_job_registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xxl_job_user`
--

DROP TABLE IF EXISTS `xxl_job_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xxl_job_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码加密信息',
  `token` varchar(100) DEFAULT NULL COMMENT '登录token',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xxl_job_user`
--

LOCK TABLES `xxl_job_user` WRITE;
/*!40000 ALTER TABLE `xxl_job_user` DISABLE KEYS */;
INSERT INTO `xxl_job_user` (`id`, `username`, `password`, `token`, `role`, `permission`) VALUES (1,'admin','ec6f07df9583e96f55a8817d0e603510b16c5d74c005e98bf940f6a1c6be728b','1838fff2e4d44fc29b3f0662bb5845ad',1,NULL);
/*!40000 ALTER TABLE `xxl_job_user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-22 11:57:30
