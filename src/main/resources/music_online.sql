SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('1', '许巍 - 第三极', 'music/许巍 - 第三极.mp3');
INSERT INTO `music` VALUES ('3', '许巍 - 青鸟II', 'music/许巍 - 青鸟II.mp3');
INSERT INTO `music` VALUES ('4', '许巍 - 美丽的女人', 'music/许巍 - 美丽的女人.mp3');
INSERT INTO `music` VALUES ('5', '许巍 - 纯粹', 'music/许巍 - 纯粹.mp3');
INSERT INTO `music` VALUES ('6', '陈粒 - 贪得', 'music/陈粒 - 贪得.mp3');
INSERT INTO `music` VALUES ('7', '许巍 - 闪亮的瞬间', 'music/许巍 - 闪亮的瞬间.mp3');
INSERT INTO `music` VALUES ('8', '许巍 - 晨星', 'music/许巍 - 晨星.mp3');
INSERT INTO `music` VALUES ('9', '许巍 - 彩虹', 'music/许巍 - 彩虹.mp3');
INSERT INTO `music` VALUES ('10', '许巍 - 爱', 'music/许巍 - 爱.mp3');
INSERT INTO `music` VALUES ('11', '许巍 - 永恒', 'music/许巍 - 永恒.mp3');
INSERT INTO `music` VALUES ('12', '陈粒 - 七楼', 'music/陈粒 - 七楼.mp3');
INSERT INTO `music` VALUES ('13', '陈粒 - 绝对占有 相对自由', 'music/陈粒 - 绝对占有 相对自由.mp3');
INSERT INTO `music` VALUES ('14', '陈粒 - 如也', 'music/陈粒 - 如也.mp3');
INSERT INTO `music` VALUES ('15', '许巍 - 灿烂', 'music/许巍 - 灿烂.mp3');
INSERT INTO `music` VALUES ('16', '陈粒 - 脱缰', 'music/陈粒 - 脱缰.mp3');
INSERT INTO `music` VALUES ('17', '许巍 - 幸福', 'music/许巍 - 幸福.mp3');
INSERT INTO `music` VALUES ('18', '陈粒 - 光', 'music/陈粒 - 光.mp3');
INSERT INTO `music` VALUES ('19', '许巍 - 自由自在', 'music/许巍 - 自由自在.mp3');
INSERT INTO `music` VALUES ('20', '陈粒 - 不灭', 'music/陈粒 - 不灭.mp3');
INSERT INTO `music` VALUES ('21', '许巍 - 曾经的你', 'music/许巍 - 曾经的你.mp3');
INSERT INTO `music` VALUES ('22', '许巍 - 那一年', 'music/许巍 - 那一年.mp3');
INSERT INTO `music` VALUES ('23', '许巍 - 爱情', 'music/许巍 - 爱情.mp3');
INSERT INTO `music` VALUES ('24', '许巍 - 心愿', 'music/许巍 - 心愿.mp3');
INSERT INTO `music` VALUES ('25', '许巍 - 彩云之巅', 'music/许巍 - 彩云之巅.mp3');
INSERT INTO `music` VALUES ('27', '陈粒 - 易燃易爆炸', 'music/陈粒 - 易燃易爆炸.mp3');
INSERT INTO `music` VALUES ('28', '许巍 - 路的尽头', 'music/许巍 - 路的尽头.mp3');
INSERT INTO `music` VALUES ('29', '许巍 - 难忘的一天', 'music/许巍 - 难忘的一天.mp3');
INSERT INTO `music` VALUES ('30', '陈粒 - 历历万乡', 'music/陈粒 - 历历万乡.mp3');
INSERT INTO `music` VALUES ('33', '陈粒 - 正趣果上果', 'music/陈粒 - 正趣果上果.mp3');
INSERT INTO `music` VALUES ('34', '许巍 - 逍遥行', 'music/许巍 - 逍遥行.mp3');
INSERT INTO `music` VALUES ('36', '陈粒 - 奇妙能力歌', 'music/陈粒 - 奇妙能力歌.mp3');
INSERT INTO `music` VALUES ('38', 'Panama+-+Matteo', 'music/Panama+-+Matteo.mp3');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '管理员');
INSERT INTO `permission` VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) CHARACTER SET big5 NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permission` (`permission_id`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin@qq.com', '1');
INSERT INTO `user` VALUES ('2', 'aaa', 'aaaaaa', 'aaa@qq.com', '2');
