# ureport
springboot集成ureport并实现对数据的存储功能(mysql)
#使用方法
分别在application.yml和DatabaseUtils.java中对数据库进行配置
#数据库
数据库表名：ureport_filety

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ureport_filety
-- ----------------------------
DROP TABLE IF EXISTS `ureport_filety`;
CREATE TABLE `ureport_filety` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` mediumblob,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
