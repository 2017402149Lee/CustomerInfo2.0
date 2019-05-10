/*
Navicat MySQL Data Transfer

Source Server         : 47.106.183.43
Source Server Version : 50725
Source Host           : 47.106.183.43:3306
Source Database       : customer

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-10 08:15:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` varchar(100) NOT NULL,
  `key` varchar(100) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `disclose` int(1) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `nation` varchar(100) DEFAULT NULL,
  `addr` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `create_time` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `otherinfo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1555905512324', '杨小小', '0', '15085672011', '1', '20', '汉', '贵州剑河', '罗经理', '1555905363823', '2019-04-22 11:58:32.324', '7', '2019-04-22', '1001', 'aaa');
INSERT INTO `customer` VALUES ('1555907337252', '欧秀竹', '0', '15085217331', '0', '大概20左右', '不详', '', '应该是护理专业的，已加微信', '1555906426311', '2019-04-22 12:28:57.252', '2', '2019-04-24', '1002', null);
INSERT INTO `customer` VALUES ('1556248705586', '刘东杰', '1', '18286139547', '0', '26', '苗', '', '已加微信。', '1555906426311', '2019-04-26 11:18:25.586', '2', '2019-04-29', '1002', null);
INSERT INTO `customer` VALUES ('1557118077991', '吴丹', '0', '15186091614', '0', '22', '汉族', '凯里学院今年毕业的学生', '微信就是手机号', '1557117450715', '2019-05-06 12:47:57.991', '2', '2019-05-06', '1002', 'aaa');
INSERT INTO `customer` VALUES ('1557119498169', '吴胜兰', '0', '15286334409', '0', '', '', '施秉县', '微信号：WU11975，已加微信，等待同意（5月7日）', '1557117450715', '2019-05-06 13:11:38.169', '2', null, '1001', 'aaa');
INSERT INTO `customer` VALUES ('1557138652395', '陆大富', '1', '15008556885', '0', '', '', '广播电视大学', '尝试微信联系，等待同意中', '1557117450715', '2019-05-06 18:30:52.395', '2', '2019-05-06', '1002', 'aaa');
INSERT INTO `customer` VALUES ('1557139374916', '胡禄江', '1', '18585554207', '1', '', '', '', '5月7日：已报名，华中师范大学，高升专，行政管理，已交150报名费，录取交学费', '1557117450715', '2019-05-06 18:42:54.916', '6', '2019-05-06', '1001', null);
INSERT INTO `customer` VALUES ('1557158777232', '姜天龙', '1', '18885045616', '1', '29', '汉', '福润集团', '尝试微信添加中，等待同意', '1557158617588', '2019-05-07 00:06:17.232', '7', null, '1002', 'aaa');
INSERT INTO `customer` VALUES ('1557211953104', '彭阐义', '0', '18385710841', '0', '21', '', '岑巩县', '我们学校毕业的学生 ，5月7日15点03分：已添加微信，等待同意', '1557117450715', '2019-05-07 14:52:33.104', '2', null, '1001', 'aaa');
INSERT INTO `customer` VALUES ('1557212912505', '杨胜海', '1', '15085288486', '1', '', '', '施秉县', '微信号：1158061774，5月7日，已加微信，等待具体沟通，5月8日：意向全日制教育', '1557117450715', '2019-05-07 15:08:32.505', '2', '2019-05-07', '1002', 'aaa');
INSERT INTO `customer` VALUES ('1557302508867', '王卜高', '1', '18744847605', '0', '', '', '天柱县上班', '她的工作是汽修：5月8日：微信已加，等待同意。', '1557117450715', '2019-05-08 16:01:48.867', '2', null, '1001', 'aaa');
INSERT INTO `customer` VALUES ('1557312672748', '伍长军', '1', '13588663546', '0', '27', '汉', '施秉县公安局交警大队', '5月9日：已经加微信，等待同意。', '1557218042486', '2019-05-08 18:51:12.748', '2', null, '1001', 'aaa');

-- ----------------------------
-- Table structure for `customerType`
-- ----------------------------
DROP TABLE IF EXISTS `customerType`;
CREATE TABLE `customerType` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type_no` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerType
-- ----------------------------
INSERT INTO `customerType` VALUES ('1555178294661', '高升专', '1001', null);
INSERT INTO `customerType` VALUES ('1555178389608', '专升本', '1002', null);
INSERT INTO `customerType` VALUES ('1555178409629', '非全日制研究生', '1003', null);

-- ----------------------------
-- Table structure for `levelRecord`
-- ----------------------------
DROP TABLE IF EXISTS `levelRecord`;
CREATE TABLE `levelRecord` (
  `id` varchar(100) NOT NULL,
  `before` varchar(100) DEFAULT NULL,
  `after` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `user_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of levelRecord
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` varchar(100) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `release_time` date DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `reading` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1555905107459', '创建团队', '你已于2019年04月22日11时51分47秒加入了团队', '1555500760276', '2019-04-22', null, '0', '1555500760276');
INSERT INTO `news` VALUES ('1555905423683', '邀请加入团队', '你已于2019年04月22日11时57分03秒被邀请加入了团队', '1555500760276', '2019-04-22', null, '0', '1555905363823');
INSERT INTO `news` VALUES ('1555905648571', '邀请加入团队', '你已于2019年04月22日12时00分48秒被邀请加入了团队', '1555500760276', '2019-04-22', null, '0', '1555905363823');
INSERT INTO `news` VALUES ('1555906525899', '邀请加入团队', '你已于2019年04月22日12时15分25秒被邀请加入了团队', '1555500760276', '2019-04-22', null, '0', '1555906426311');
INSERT INTO `news` VALUES ('1555997128377', '邀请加入团队', '你已于2019年04月23日13时25分28秒被邀请加入了团队', '1555500760276', '2019-04-23', null, '0', '1555996742036');
INSERT INTO `news` VALUES ('1555997872637', '创建团队', '你已于2019年04月23日13时37分52秒加入了团队', '1555996742036', '2019-04-23', null, '0', '1555996742036');
INSERT INTO `news` VALUES ('1556525094023', '邀请加入团队', '你已于2019年04月29日16时04分54秒被邀请加入了团队', '1555500760276', '2019-04-29', null, '0', '1556523261087');
INSERT INTO `news` VALUES ('1556525106436', '邀请加入团队', '你已于2019年04月29日16时05分06秒被邀请加入了团队', '1555500760276', '2019-04-29', null, '0', '1556523429034');
INSERT INTO `news` VALUES ('1556525120318', '邀请加入团队', '你已于2019年04月29日16时05分20秒被邀请加入了团队', '1555500760276', '2019-04-29', null, '0', '1556523753391');
INSERT INTO `news` VALUES ('1556525131917', '邀请加入团队', '你已于2019年04月29日16时05分31秒被邀请加入了团队', '1555500760276', '2019-04-29', null, '0', '1556524279608');
INSERT INTO `news` VALUES ('1557038708668', '邀请加入团队', '你已于2019年05月05日14时45分08秒被邀请加入了团队', '1555500760276', '2019-05-05', null, '0', '1555997969117');
INSERT INTO `news` VALUES ('1557060400267', '创建团队', '你已于2019年05月05日20时46分40秒加入了团队', '1557060359379', '2019-05-05', null, '0', '1557060359379');
INSERT INTO `news` VALUES ('1557060413288', '邀请加入团队', '你已于2019年05月05日20时46分53秒被邀请加入了团队', '1557060359379', '2019-05-05', null, '0', '1557060287385');
INSERT INTO `news` VALUES ('1557060441421', '邀请加入团队', '你已于2019年05月05日20时47分21秒被邀请加入了团队', '1557060359379', '2019-05-05', null, '0', '1557060287385');
INSERT INTO `news` VALUES ('1557060601016', '创建团队', '你已于2019年05月05日20时50分01秒加入了团队', '1557060547307', '2019-05-05', null, '0', '1557060547307');
INSERT INTO `news` VALUES ('1557060607790', '邀请加入团队', '你已于2019年05月05日20时50分07秒被邀请加入了团队', '1557060547307', '2019-05-05', null, '0', '1557060287385');
INSERT INTO `news` VALUES ('1557129535416', '创建团队', '你已于2019年05月06日15时58分55秒加入了团队', '1555996742036', '2019-05-06', null, '0', '1555996742036');
INSERT INTO `news` VALUES ('1557130965020', '邀请加入团队', '你已于2019年05月06日16时22分45秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557129423150');
INSERT INTO `news` VALUES ('1557131721952', '邀请加入团队', '你已于2019年05月06日16时35分21秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557131300799');
INSERT INTO `news` VALUES ('1557131732392', '邀请加入团队', '你已于2019年05月06日16时35分32秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557131338308');
INSERT INTO `news` VALUES ('1557131743644', '邀请加入团队', '你已于2019年05月06日16时35分43秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557131559485');
INSERT INTO `news` VALUES ('1557132091631', '邀请加入团队', '你已于2019年05月06日16时41分31秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557131929769');
INSERT INTO `news` VALUES ('1557132107911', '邀请加入团队', '你已于2019年05月06日16时41分47秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132017207');
INSERT INTO `news` VALUES ('1557132764835', '邀请加入团队', '你已于2019年05月06日16时52分44秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132162809');
INSERT INTO `news` VALUES ('1557132776940', '邀请加入团队', '你已于2019年05月06日16时52分56秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132215115');
INSERT INTO `news` VALUES ('1557132794720', '邀请加入团队', '你已于2019年05月06日16时53分14秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132457205');
INSERT INTO `news` VALUES ('1557132808942', '邀请加入团队', '你已于2019年05月06日16时53分28秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132540776');
INSERT INTO `news` VALUES ('1557132821022', '邀请加入团队', '你已于2019年05月06日16时53分41秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132619996');
INSERT INTO `news` VALUES ('1557132837341', '邀请加入团队', '你已于2019年05月06日16时53分57秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132672467');
INSERT INTO `news` VALUES ('1557132849611', '邀请加入团队', '你已于2019年05月06日16时54分09秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557132724275');
INSERT INTO `news` VALUES ('1557134377755', '邀请加入团队', '你已于2019年05月06日17时19分37秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557134292599');
INSERT INTO `news` VALUES ('1557134392499', '邀请加入团队', '你已于2019年05月06日17时19分52秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557134336622');
INSERT INTO `news` VALUES ('1557134669417', '邀请加入团队', '你已于2019年05月06日17时24分29秒被邀请加入了团队', '1555500760276', '2019-05-06', null, '0', '1557134590892');
INSERT INTO `news` VALUES ('1557225170173', '创建团队', '你已于2019年05月07日18时32分50秒加入了团队', '1557117450715', '2019-05-07', null, '0', '1557117450715');
INSERT INTO `news` VALUES ('1557240550693', '邀请加入团队', '你已于2019年05月07日22时49分10秒被邀请加入了团队', '1557117450715', '2019-05-07', null, '0', '1557218042486');
INSERT INTO `news` VALUES ('1557304298491', '邀请加入团队', '你已于2019年05月08日16时31分38秒被邀请加入了团队', '1557117450715', '2019-05-08', null, '0', '1557304119295');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1555138481526', '客服人员', '{\"c101\":0,\"c100\":1,\"c103\":1,\"c102\":1,\"c104\":1}');
INSERT INTO `role` VALUES ('1555138505019', '普通用戶', '{\"c101\":0,\"c100\":0,\"c103\":0,\"c102\":0,\"c104\":0}');
INSERT INTO `role` VALUES ('1555148650901', '超级管理员', '{\"c101\":1,\"c100\":1,\"c103\":1,\"c102\":1,\"c104\":1}');

-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1555905107431', '吴老师的小伙伴', '1555500760276', '留意身边的小伙伴，别让资源被别人撸走！', '1', '2019-04-22');
INSERT INTO `team` VALUES ('1557129535382', '志远小团队1', '1555996742036', '一切向钱看', '1', '2019-05-06');
INSERT INTO `team` VALUES ('1557225170143', '提升学历凯里888', '1557117450715', '提升学历，专科，本科', '1', '2019-05-07');

-- ----------------------------
-- Table structure for `teamers`
-- ----------------------------
DROP TABLE IF EXISTS `teamers`;
CREATE TABLE `teamers` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `team_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teamers
-- ----------------------------
INSERT INTO `teamers` VALUES ('1555905107449', '1555500760276', null, '1', '2019-04-22', '1555905107431');
INSERT INTO `teamers` VALUES ('1555906525888', '1555906426311', null, '0', '2019-04-22', '1555905107431');
INSERT INTO `teamers` VALUES ('1556525094012', '1556523261087', null, '0', '2019-04-29', '1555905107431');
INSERT INTO `teamers` VALUES ('1556525106422', '1556523429034', null, '0', '2019-04-29', '1555905107431');
INSERT INTO `teamers` VALUES ('1556525120308', '1556523753391', null, '0', '2019-04-29', '1555905107431');
INSERT INTO `teamers` VALUES ('1556525131904', '1556524279608', null, '0', '2019-04-29', '1555905107431');
INSERT INTO `teamers` VALUES ('1557129535402', '1555996742036', null, '1', '2019-05-06', '1557129535382');
INSERT INTO `teamers` VALUES ('1557130965010', '1557129423150', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557131721940', '1557131300799', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557131732380', '1557131338308', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557131743633', '1557131559485', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132091619', '1557131929769', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132107895', '1557132017207', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132764823', '1557132162809', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132776927', '1557132215115', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132794707', '1557132457205', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132808931', '1557132540776', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132821005', '1557132619996', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132837325', '1557132672467', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557132849598', '1557132724275', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557134377745', '1557134292599', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557134392485', '1557134336622', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557134669407', '1557134590892', null, '0', '2019-05-06', '1555905107431');
INSERT INTO `teamers` VALUES ('1557225170163', '1557117450715', null, '1', '2019-05-07', '1557225170143');
INSERT INTO `teamers` VALUES ('1557240550681', '1557218042486', null, '0', '2019-05-07', '1557225170143');
INSERT INTO `teamers` VALUES ('1557304298481', '1557304119295', null, '0', '2019-05-08', '1557225170143');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `sex` int(1) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `level` int(1) NOT NULL,
  `status` int(1) NOT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1555138505020', 'admin', '1', 'admin', 'admin', '1', '1', '1555148650901');
INSERT INTO `user` VALUES ('1555500606748', '殷文华', '1', '102938', '13750218014', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1555500659685', '唐玉美', '0', '20011012', '17674065174', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1555500760276', '吴剑涛', '1', '123456', '15085672014', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1555906426311', '杨小赟', '0', '18212389710', '18212389710', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1555996742036', '吴剑波', '1', '123456', '18585590227', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556074527332', '吴剑平', '1', 'yan556400', '18212314709', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556242322937', '杨雅', '0', 'zzxxcc..', '17607242847', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556354096251', '张皆能', '1', 'zjn0608', '13553918465', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556360807207', '陈龙', '1', '142536', '13639096323', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556523261087', '周燕芳', '0', '123456', '15885653903', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556523429034', '欧美伶', '0', '123456', '18188154496', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556523753391', '梁开宇', '1', '123456', '13618550624', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556524279608', '肖彬', '1', '123456', '15761634460', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556525317776', '卫江', '1', '123456', '17785181368', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556592595334', '廖思栋', '1', 'liaosidong', '18933808581', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556593191396', '麦宇航', '1', '19990610meixian', '18312689438', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556627568294', '肖浩纯', '0', '200005', '13760596974', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1556984943074', '马真理', '1', 'mmm123456', '17585156914', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557060359379', '韦乾来', '1', '123456', '18224995161', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557117450715', '唐华宏', '1', 'thh520', '15008554756', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557128425848', '吴剑江', '1', '111212', '18685505881', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557129423150', '吴才诚', '1', '123456', '18985843961', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557131300799', '毛智恒', '1', '123456', '18386710664', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557131338308', '杨帅', '1', '123456', '15285074715', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557131559485', '张翰', '1', '123456', '18785590490', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557131929769', '杨绍坛', '1', '123456', '18708557187', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132017207', '吴少健', '1', '123456', '18386720220', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132162809', '肖燕子', '0', '123456', '18084325246', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132215115', '张妹纪', '0', '123456', '15329980666', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132457205', '刘倩', '0', '123456', '13985829406', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132540776', '杨昌碧', '0', '123456', '13984453937', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132619996', '彭兴宇', '1', '123456', '15286357093', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132672467', '姚茂琼', '0', '123456', '13595596670', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557132724275', '周承英', '0', '123456', '13595590666', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557134144471', '张泽炼', '1', '517221', '18585503018', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557134292599', '蒋承坤', '1', '123456', '17584700296', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557134336622', '李思阳', '1', '123456', '18586522553', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557134590892', '龚文峰', '1', '123456', '18798512208', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557158617588', '黄万青', '0', '198965', '18786052641', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557200923155', '杨敏', '0', '900709', '18275225420', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557206652279', '杨俊', '1', '582948', '18184523767', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557212197160', 'kf1', '1', '123456', 'kf1', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212238390', 'kf2', '1', '123456', 'kf2', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212252998', 'kf3', '1', '123456', 'kf3', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212274031', 'kf4', '1', '123456', 'kf4', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212291960', 'kf5', '1', '123456', 'kf5', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212306479', 'kf6', '1', '123456', 'kf6', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212321518', 'kf7', '1', '123456', 'kf7', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212339230', 'kf8', '1', '123456', 'kf8', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212353384', 'kf9', '1', '123456', 'kf9', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557212366967', 'kf10', '1', '123456', 'kf10', '0', '1', '1555138481526');
INSERT INTO `user` VALUES ('1557218042486', '胡禄江', '1', '888888', '18585554207', '0', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557229773347', '黄钰凯', '1', '961010', '18798418977', '1', '1', '1555138505019');
INSERT INTO `user` VALUES ('1557304119295', '杨光泽', '1', 'ygz19941015', '18375291312', '0', '1', '1555138505019');

-- ----------------------------
-- Table structure for `userintegral`
-- ----------------------------
DROP TABLE IF EXISTS `userintegral`;
CREATE TABLE `userintegral` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `total` int(10) DEFAULT NULL,
  `updata_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userintegral
-- ----------------------------

-- ----------------------------
-- Table structure for `userintegralDetail`
-- ----------------------------
DROP TABLE IF EXISTS `userintegralDetail`;
CREATE TABLE `userintegralDetail` (
  `id` varchar(100) NOT NULL,
  `intergral_id` varchar(100) DEFAULT NULL,
  `number` int(10) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userintegralDetail
-- ----------------------------
