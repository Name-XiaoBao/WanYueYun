/*
Navicat MySQL Data Transfer

Source Server         : 挽悦云
Source Server Version : 50740
Source Host           : broadcast.xbjstd.cn:1564
Source Database       : wanyueyun

Target Server Type    : MYSQL
Target Server Version : 50740
File Encoding         : 65001

Date: 2023-12-16 18:52:52
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `user` varchar(255) NOT NULL,
    `pass` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=818 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for apiuser
-- ----------------------------
DROP TABLE IF EXISTS `apiuser`;
CREATE TABLE `apiuser`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `NameApi`        varchar(255) NOT NULL,
    `user`           varchar(255) NOT NULL,
    `pass`           varchar(255) NOT NULL,
    `Integral`       int(255) DEFAULT NULL,
    `MembershipTime` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=991 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for carmi
-- ----------------------------
DROP TABLE IF EXISTS `carmi`;
CREATE TABLE `carmi`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `Api`     varchar(255) NOT NULL,
    `Type`    varchar(255) NOT NULL,
    `Carmi`   varchar(255) NOT NULL,
    `Content` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2607890 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`
(
    `id`  int(11) NOT NULL AUTO_INCREMENT,
    `img` varchar(255) NOT NULL,
    `url` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for edition
-- ----------------------------
DROP TABLE IF EXISTS `edition`;
CREATE TABLE `edition`
(
    `id`                   int(11) NOT NULL AUTO_INCREMENT,
    `title`                varchar(255) NOT NULL,
    `WebEdition`           varchar(255) NOT NULL,
    `AndroidVersion`       varchar(255) NOT NULL,
    `AndroidDownload`      varchar(255) NOT NULL,
    `VersionUpdateContent` longtext     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for file_url
-- ----------------------------
DROP TABLE IF EXISTS `file_url`;
CREATE TABLE `file_url`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `Api`      varchar(255) NOT NULL,
    `FileName` varchar(255) NOT NULL,
    `Url`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2302 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `Title`   varchar(255) NOT NULL,
    `Content` longtext     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for links
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `url`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mini_program
-- ----------------------------
DROP TABLE IF EXISTS `mini_program`;
CREATE TABLE `mini_program`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `icon`  varchar(255) NOT NULL,
    `url`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `Title`       varchar(255) NOT NULL,
    `Content`     varchar(255) NOT NULL,
    `ReleaseTime` datetime     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for remote_document
-- ----------------------------
DROP TABLE IF EXISTS `remote_document`;
CREATE TABLE `remote_document`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `Api`          varchar(255) NOT NULL,
    `Jurisdiction` int(255) DEFAULT '0',
    `SecretKey`    varchar(255) DEFAULT NULL,
    `Title`        varchar(255) DEFAULT NULL,
    `Content`      longtext,
    `UpdateTime`   datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=642 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for start_statistics
-- ----------------------------
DROP TABLE IF EXISTS `start_statistics`;
CREATE TABLE `start_statistics`
(
    `id`  int(11) NOT NULL AUTO_INCREMENT,
    `Api` varchar(255) NOT NULL,
    `Num` int(11) DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for third_party_login
-- ----------------------------
DROP TABLE IF EXISTS `third_party_login`;
CREATE TABLE `third_party_login`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `username`       varchar(255) NOT NULL,
    `Identification` varchar(255) NOT NULL,
    `UserKey`        varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `user`         varchar(255) NOT NULL,
    `pass`         varchar(255) NOT NULL,
    `Jurisdiction` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    KEY            `pass` (`pass`)
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8mb4;
