#训练平台项目

##建表语句

-- -----------------------------------------------------
-- Schema training_platform
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `training_platform` DEFAULT CHARACTER SET utf8 ;
USE `training_platform` ;


-- -----------------------------------------------------
-- Table `training_platform`.`model`
-- -----------------------------------------------------
CREATE TABLE `model` (
`model_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`model_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型名称' ,
`model_describe`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模型描述' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
`update_time`  datetime NOT NULL COMMENT '更新时间' ,
`model_type`  tinyint(2) NOT NULL COMMENT '模型类别：' ,
PRIMARY KEY (`model_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2
ROW_FORMAT=DYNAMIC
;


-- -----------------------------------------------------
-- Table `training_platform`.`dataset`
-- -----------------------------------------------------
CREATE TABLE `dataset` (
`dataset_id`  int(11) NOT NULL AUTO_INCREMENT ,
`dataset_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据集名称' ,
`create_time`  datetime NOT NULL ,
`update_time`  datetime NOT NULL ,
`dataset_type`  tinyint(2) NOT NULL COMMENT '数据集类型：1-训练集 2-校验集' ,
PRIMARY KEY (`dataset_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2
ROW_FORMAT=DYNAMIC
;

-- -----------------------------------------------------
-- Table `training_platform`.`label`
-- -----------------------------------------------------
CREATE TABLE `label` (
`label_id`  int(11) NOT NULL AUTO_INCREMENT ,
`label_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称' ,
`images`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签下面的图片' ,
`create_time`  datetime NOT NULL ,
`update_time`  datetime NOT NULL ,
`dataset_id`  int(11) NOT NULL ,
PRIMARY KEY (`label_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3
ROW_FORMAT=DYNAMIC
;




-- -----------------------------------------------------
-- Table `training_platform`.`application`
-- -----------------------------------------------------
CREATE TABLE `NewTable` (
`application_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
`application_type`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用类型' ,
`application_version`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '版本' ,
`training_status`  tinyint(2) NOT NULL ,
`training_start_time`  datetime NOT NULL ,
`training_end_time`  datetime NULL DEFAULT NULL ,
`appraisal_report`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评估报告' ,
`model_id`  int(11) NOT NULL ,
`dataset_id`  int(11) NOT NULL ,
`label_ids`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签id' ,
PRIMARY KEY (`application_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
;

##swagger2文档地址<br/>
http://localhost:2029/training/platform/doc.html



