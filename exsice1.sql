-- 创建数据库
CREATE DATABASE excise1;

-- 使用数据库
USE excise1;

-- 创建表
CREATE TABLE `t_downloadlist` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '表示',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '显示名称',
  `path` VARCHAR(255) DEFAULT NULL COMMENT '存放路径及文件名',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
  `size` BIGINT(20) DEFAULT NULL COMMENT '文件大小',
  `star` INT(11) DEFAULT NULL COMMENT '星级',
  `image` VARCHAR(255) DEFAULT NULL COMMENT '图片路径及名称',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user` (
  `userName` VARCHAR(255) NOT NULL COMMENT '用户名',
  `password` VARCHAR(255) DEFAULT NULL COMMENT '密码',
  `chrName` VARCHAR(255) DEFAULT NULL COMMENT '中文名',
  `role` VARCHAR(255) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`userName`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
