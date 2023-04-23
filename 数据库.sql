/*
Navicat MySQL Data Transfer

Source Server         : 小暴的本地数据库
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : backstage

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2023-04-23 15:31:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of api
-- ----------------------------

-- ----------------------------
-- Table structure for apiuser
-- ----------------------------
DROP TABLE IF EXISTS `apiuser`;
CREATE TABLE `apiuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NameApi` varchar(255) NOT NULL,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `Integral` varchar(255) DEFAULT NULL,
  `MembershipTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of apiuser
-- ----------------------------

-- ----------------------------
-- Table structure for edition
-- ----------------------------
DROP TABLE IF EXISTS `edition`;
CREATE TABLE `edition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `WebEdition` varchar(255) NOT NULL,
  `AndroidVersion` varchar(255) NOT NULL,
  `AndroidDownload` varchar(255) NOT NULL,
  `VersionUpdateContent` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of edition
-- ----------------------------

-- ----------------------------
-- Table structure for file_url
-- ----------------------------
DROP TABLE IF EXISTS `file_url`;
CREATE TABLE `file_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Api` varchar(255) NOT NULL,
  `Url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of file_url
-- ----------------------------

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Content` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES ('1', 'API用户注册', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/Register\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名=username\nAPI名字=name \r\nAPI用户名=user\r\nAPI用户密码=pass\r\n</pre>');
INSERT INTO `interface` VALUES ('2', 'API用户登录', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/login\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名=username\nAPI名字=name \r\nAPI用户名=user\r\nAPI用户密码=pass\r\n</pre>');
INSERT INTO `interface` VALUES ('3', 'API用户列表', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/UserList\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名=username\r\n密码=password\r\nAPI名字=name\r\n</pre>');
INSERT INTO `interface` VALUES ('4', 'API用户信息', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/UserInfo\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名 = username\nAPI名字 = name\r\nAPI用户名 = user\r\nAPI用户密码 = pass\r\n</pre>');
INSERT INTO `interface` VALUES ('5', '修改API用户密码', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/UpPass\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名 = username\r\n密码 = password\r\nAPI名字 = name\r\nAPI用户ID = id\r\nAPI用户名 = user\r\nAPI用户密码 = pass\r\nAPI用户新密码 = Newpass\r\n</pre>');
INSERT INTO `interface` VALUES ('6', '修改API用户积分', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/upIntegral\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名 = username\r\n密码 = password\r\nAPI名字 = name\r\nAPI用户ID = id\r\nAPI用户名 = user\r\nAPI用户密码 = pass\r\nAPI用户积分 = Integral\r\n</pre>');
INSERT INTO `interface` VALUES ('7', '修改API用户会员时间', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/upMembershipTime\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名 = username\r\n密码 = password\r\nAPI名字 = name\r\nAPI用户ID = id\r\nAPI用户名 = user\r\nAPI用户密码 = pass\r\nAPI用户会员时间 = MembershipTime\r\n<span style=\"color: red\">注意：时间格式应为yyyy-MM-dd HH:mm:ss</span>\r\n</pre>');
INSERT INTO `interface` VALUES ('8', '删除API用户', '<pre>\r\nURL：http://wanyue.xbnb666.top/api/deluser\r\n请求方式：GET、POST\r\n请求参数：\r\n用户名 = username\r\n密码 = password\r\nAPI名字 = name\r\nAPI用户名 = user\r\n</pre>');
INSERT INTO `interface` VALUES ('9', '发送邮箱验证码', '<pre>\r\nURL：http://wanyue.xbnb666.top/VerificationCode\n请求方式：POST\r\n请求参数：\r\n邮箱账号 = username\n<span style=\"color: red\">注意：间隔时间为一分钟</span>\n</pre>');
INSERT INTO `interface` VALUES ('10', '用户反馈', '<pre>\r\nURL：http://wanyue.xbnb666.top/UserFeedback\n请求方式：GET,POST\r\n请求参数：\r\n邮箱账号 = email\nAPI名称 = Api\n反馈内容 = Content\n联系方式 = ContactInformation\n<span style=\"color: red\">注意：间隔时间为三分钟</span>\n</pre>');
INSERT INTO `interface` VALUES ('11', 'MD5加密', '<pre>\r\nURL： http://wanyue.xbnb666.top/Md5\n请求方式：POST\r\n请求参数：\r\n内容 = text\n</pre>');
INSERT INTO `interface` VALUES ('12', '获取API文档', '<pre>\r\nURL： http://wanyue.xbnb666.top/api/RemoteDocument\n请求方式：GET,POST\r\n请求参数：\r\nAPI名称 = Api\n文档ID = id\nAPI密钥 = SecretKey\n<span style=\"color: red\">注意：如未设置SecretKey的话，SecretKey参数可以为空，您也可以使用\"修改文档\"页面的\"复制链接\"功能来快速获取文档链接，参数会自动生成并复制</span>\n</pre>');
INSERT INTO `interface` VALUES ('13', '发送邮件', '<pre>\r\nURL：http://wanyue.xbnb666.top/email\n请求方式：GET,POST\r\n请求参数：\r\n邮箱账号 = email\n标题 = title\n内容 = content\n<span style=\"color: red\">注意：间隔时间为三分钟</span>\n</pre>');
INSERT INTO `interface` VALUES ('14', '增加API启动次数', '<pre>\r\nURL：http://wanyue.xbnb666.top/AddStartStatistics\r\n请求方式：GET,POST\r\n请求参数：\r\nAPI名称 = Api\r\n<span style=\"color: red\">注意：三分钟内只能访问一次，访问一次+1</span>\r\n</pre>');
INSERT INTO `interface` VALUES ('15', '获取API启动次数', '<pre>\r\nURL：http://wanyue.xbnb666.top/StartStatistics\r\n请求方式：GET,POST\r\n请求参数：\r\nAPI名称 = Api\r\n</pre>');
INSERT INTO `interface` VALUES ('16', '云储存-上传API文件', '<pre>\r\nURL：http://wanyue.xbnb666.top/upload\r\n请求方式：POST\r\n请求参数：\r\n挽悦云账号 = username\r\nAPI名称 = Api\r\n文件 = file\r\n</pre>');
INSERT INTO `interface` VALUES ('17', '云储存-查询API文件列表', '<pre>\r\nURL：http://wanyue.xbnb666.top/file\r\n请求方式：GET、POST\r\n请求参数：\r\n挽悦云账号 = username\r\nAPI名称 = Api\r\n</pre>');
INSERT INTO `interface` VALUES ('18', '云储存-删除API文件', '<pre>\r\nURL：http://wanyue.xbnb666.top/DelFile\r\n请求方式：GET、POST\r\n请求参数：\r\n挽悦云账号 = username\r\nAPI名称 = Api\r\n文件名称  =  filename\r\n<span style=\"color: red\">注意：文件名称需填写文件后缀名</span>\r\n</pre>');

-- ----------------------------
-- Table structure for links
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of links
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Content` varchar(255) NOT NULL,
  `ReleaseTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for remote_document
-- ----------------------------
DROP TABLE IF EXISTS `remote_document`;
CREATE TABLE `remote_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Api` varchar(255) NOT NULL,
  `Jurisdiction` int(255) DEFAULT '0',
  `SecretKey` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Content` longtext,
  `UpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of remote_document
-- ----------------------------

-- ----------------------------
-- Table structure for start_statistics
-- ----------------------------
DROP TABLE IF EXISTS `start_statistics`;
CREATE TABLE `start_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Api` varchar(255) NOT NULL,
  `Num` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of start_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for third_party_login
-- ----------------------------
DROP TABLE IF EXISTS `third_party_login`;
CREATE TABLE `third_party_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `Identification` varchar(255) NOT NULL,
  `UserKey` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of third_party_login
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `Jurisdiction` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
