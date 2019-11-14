# ************************************************************
# Sequel Pro SQL dump
# Version 5126
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: rm-uf6rubu77v9992y344o.mysql.rds.aliyuncs.com (MySQL 5.7.25-log)
# Database: speecher_dev
# Generation Time: 2019-11-06 07:37:20 +0000
# ************************************************************


create database if not exists speecher_dev default charset utf8 collate utf8_general_ci;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `signin_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
-- admin   A111111
INSERT INTO `admin` (`id`, `username`, `name`, `password`, `role_id`, `status`, `signin_at`)
VALUES
	(2,'admin','超级管理员','c4723c465300cf66978f39ccf83f0adc',1,1,1572956993736),
	(4,'mk','mk','b353b316e9154515f17cb4fdf063d408',3,1,1572956957674);

/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table admin_session
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin_session`;

CREATE TABLE `admin_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `role` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `token` varchar(64) DEFAULT NULL,
  `signin_at` bigint(20) DEFAULT NULL,
  `expire_at` bigint(20) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `location` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin_session` WRITE;
/*!40000 ALTER TABLE `admin_session` DISABLE KEYS */;

INSERT INTO `admin_session` (`id`, `admin_id`, `role`, `name`, `token`, `signin_at`, `expire_at`, `ip`, `location`)
VALUES
	(1,2,'超级管理员','超级管理员','f44605d1e7601abf81c8cda46ad350ac',1572934663008,1573107463008,'0:0:0:0:0:0:0:1',''),
	(2,2,'超级管理员','超级管理员','744f084b369ae79d93c7749daccafff2',1572934723488,1573107523488,'0:0:0:0:0:0:0:1',''),
	(3,2,'超级管理员','超级管理员','f949a0ca28270e34b4a749c45d8ffb85',1572953958800,1573126758800,'0:0:0:0:0:0:0:1',''),
	(4,4,'初级管理员','mk','27090c1feb01d0420bb2a3bf6caad633',1572956957474,1573129757474,'0:0:0:0:0:0:0:1',''),
	(5,2,'超级管理员','超级管理员','ee8eaf2a90d05374a25d3d2c68886632',1572956993581,1573129793581,'0:0:0:0:0:0:0:1','');

/*!40000 ALTER TABLE `admin_session` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table article
# ------------------------------------------------------------

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(512) DEFAULT NULL,
  `name` varchar(512) DEFAULT NULL,
  `img` varchar(1024) DEFAULT NULL,
  `intro` varchar(512) DEFAULT NULL,
  `content` longtext,
  `release_time` bigint(20) DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;

INSERT INTO `article` (`id`, `title`, `name`, `img`, `intro`, `content`, `release_time`, `created_at`, `status`, `type`)
VALUES
	(1,'sadjkas','万能的哈哈','http://fs.maidaotech.cn/zyedu/f/2019/11/2/5dbd5380dc82c2a47c6f8591oj3udrcm.png','sd,asndand','<p>sadad</p>',1574853248089,1572688836960,1,1),
	(3,'wqeq','qweq','http://fs.maidaotech.cn/zyedu/f/2019/11/2/5dbd6eb0dc82c2a621e2e67c3pv6y9mo.png','eqw','<p>eqq</p>',1572954932563,1572695744908,1,2),
	(4,'关注学院最新资讯','深V是深V','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12d2f8fcf8118b5d18ed89d7335rq.png','关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯','<p>关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯</p><p><img src=\"http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12d158fcf8118b5d18ed72yzjydfx.jpg\" style=\"width: 300px;\" class=\"fr-fic fr-dib\"></p><p>关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯。</p>',1572941049597,1572941107106,1,1),
	(5,'关注学院最新资讯关注','爽肤水','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12d668fcf8118b5d18ed9y79jw7tz.png','关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯','<p>关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯<img src=\"http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12d7b8fcf8118b5d18edaw4c0fjk1.png\" style=\"width: 300px;\" class=\"fr-fic fr-dib\"></p>',1572941141166,1572941187018,1,1),
	(6,'关注学院最新资讯关注','好几十','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12d958fcf8118b5d18edbk8h5d8ow.png','关注学院最新资讯关注学院最新资讯','<p>关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯关注学院最新资讯</p>',1572941194523,1572941221317,1,2),
	(7,'introintro','家具店','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12f7e8fcf8118b5d18edcw352d565.png','回望往期课堂 领略学员风采回望往期课堂 领略学员风采回望往期课堂 领略学员风采回望往期课堂 领略学员风采','<pre>回望往期课堂 领略学员风采回望往期课堂 领略学员风回望往期课堂 领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采</pre><pre>\n</pre>',1572941662105,1572941723282,1,1),
	(8,'领略学员风采领略学员','发送','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc12fee8fcf8118b5d18eddt28ex9y7.png','领略学员风采领略学员风采','<p>领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采领略学员风采</p>',1572941792142,1572941818215,1,2);

/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table banner
# ------------------------------------------------------------

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `img` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;

INSERT INTO `banner` (`id`, `title`, `url`, `status`, `priority`, `type`, `img`)
VALUES
	(29,'6666','ksada',1,3,2,'http://fs.maidaotech.cn/zyedu/f/2019/11/2/5dbcf442dc82c299952a2031u8p52ewg.png'),
	(30,'孟凯','asdaa',1,3,1,'http://fs.maidaotech.cn/zyedu/f/2019/11/2/5dbcf51fdc82c299952a2032e1qx99om.png'),
	(31,'第一张','哈哈哈大',1,1,1,'http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc114be8fcf810fb64d3a289lfhdzug.png'),
	(32,'嘉禾街道哈·','话就是交换机',1,2,1,'http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc1152b8fcf810fb64d3a2973yth9vy.png');

/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table campus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `campus`;

CREATE TABLE `campus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `company` varchar(512) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `campus` WRITE;
/*!40000 ALTER TABLE `campus` DISABLE KEYS */;

INSERT INTO `campus` (`id`, `name`, `company`, `status`, `created_at`)
VALUES
	(26,'eqweq','qweq',1,1572675881392),
	(27,'werw','rwer',1,1572680083705),
	(28,'河南工程学院','撒大声地',1,1572919278902),
	(29,'hedasjk','sdasda',1,1572919466443),
	(30,'fsdfsdfs','qweq',1,1572675881392),
	(31,'wersdsdw','rwer',1,1572680083705),
	(32,'河南工程学院','撒大声地',1,1572919278902),
	(33,'aaasdfs','sdasda',1,1572919466443);

/*!40000 ALTER TABLE `campus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table form
# ------------------------------------------------------------

DROP TABLE IF EXISTS `form`;

CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `mobile` varchar(512) DEFAULT NULL,
  `course` varchar(10) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;

INSERT INTO `form` (`id`, `name`, `mobile`, `course`, `created_at`, `type`)
VALUES
	(24,'mk','15838374735','数学',0,1),
	(25,'213','13347625162','语文',0,1),
	(26,'mengkai','15858474735','',0,2),
	(27,'mmk','15837348498','',0,2),
	(40,'hg','13554555665',NULL,1573022146689,2),
	(42,'csd','64645653243','个人品牌塑造营',1573022880115,1);

/*!40000 ALTER TABLE `form` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table heavy_task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `heavy_task`;

CREATE TABLE `heavy_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(32) NOT NULL,
  `secret` varchar(128) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `progress` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL,
  `output` varchar(1000) DEFAULT NULL,
  `errors` text,
  PRIMARY KEY (`id`),
  KEY `owner_createdat` (`owner`,`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `heavy_task` WRITE;
/*!40000 ALTER TABLE `heavy_task` DISABLE KEYS */;

INSERT INTO `heavy_task` (`id`, `owner`, `secret`, `created_at`, `updated_at`, `progress`, `status`, `output`, `errors`)
VALUES
	(24,'1573009908703','8142696234163307716015859165958300415323389841119218650344088798',1573009908705,1573009929029,1,3,NULL,NULL),
	(25,'1573009934066','0761004401328430483912949405879030337748479620362362903143336263',1573009934066,1573009935869,1,3,NULL,NULL),
	(26,'1573010141942','7960127030280833898754907594385980387522623483056002362412880487',1573010141942,1573010148133,100,2,'http://fs.maidaotech.cn/zyedu/f/2019/11/6/5dc23ae2dc82c2e5439d707c.xlsx',NULL),
	(27,'1573010206770','4894089333146756110148414460674972500282872670393877529677699039',1573010206771,1573010210880,100,2,'http://fs.maidaotech.cn/zyedu/f/2019/11/6/5dc23b22dc82c2e5439d707d.xlsx',NULL);

/*!40000 ALTER TABLE `heavy_task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `permissions` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `name`, `permissions`)
VALUES
	(1,'超级管理员','[\"ROLE_EDIT\", \"ADMIN_LIST\", \"ADMIN_EDIT\",\"TRAINER_EDIT\",\"BANNER_EDIT\",\"CAMPUS_EDIT\",\"ARTICLE_EDIT\",\"FORM_QUERY\",\"VIDEO_EDIT\"]'),
	(3,'初级管理员','[\"BANNER_EDIT\",\"CAMPUS_EDIT\",\"ARTICLE_EDIT\",\"FORM_QUERY\",\"VIDEO_EDIT\",\"TRAINER_EDIT\"]');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table trainer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `trainer`;

CREATE TABLE `trainer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `pc_img` varchar(1000) DEFAULT NULL,
  `mob_img` varchar(1000) DEFAULT NULL,
  `intro` text,
  `status` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `master_piece` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

LOCK TABLES `trainer` WRITE;

# Dump of table video
# ------------------------------------------------------------

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `img` varchar(512) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `video_summary` varchar(140) DEFAULT NULL,
  `video_intro` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;

INSERT INTO `video` (`id`, `title`, `img`, `url`, `status`, `priority`, `type`, `created_at`, `video_summary`, `video_intro`)
VALUES
	(24,'hjsdbaj','http://fs.maidaotech.cn/zyedu/f/2019/11/3/5dbe9811dc82c2bf54f3e7d7ja2na5o4.png','http://fs.maidaotech.cn/zyedu/f/2019/11/3/5dbe9816dc82c2bf54f3e7d8lnuxjtx1.mp4',1,1,2,1572771867157,NULL,NULL),
	(25,'孟凯','http://fs.maidaotech.cn/zyedu/f/2019/11/3/5dbeb26bdc82c2c1ba2a1682525s30yc.png','http://fs.maidaotech.cn/zyedu/f/2019/11/3/5dbeb271dc82c2c1ba2a1683rq8aghhz.mp4',1,2,1,1572778614367,NULL,NULL),
	(26,'zhoujielun','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc0d7cfdc82c2c83487f7a0yjk5nome.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc0d7bbdc82c2c83487f79fmzxn04kb.mp4',1,3,2,1572919257229,NULL,NULL),
	(28,'会更好','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc137268fcf811b6a98c2b8pm167z2p.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc1380a8fcf811b6a98c2b9jm3umb5p.mp4',1,1,1,1572943886026,NULL,NULL),
	(29,'开机后果','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc138358fcf811b6a98c2bag08qhcx0.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc1383b8fcf811b6a98c2bb9eqvzr0j.mp4',1,1,1,1572943936984,NULL,NULL),
	(30,'好尴尬','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc138a88fcf811b6a98c2bcycu7vt9q.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc138ae8fcf811b6a98c2bdgduui54s.mp4',1,0,1,1572944048976,NULL,NULL),
	(31,'快快快多看看','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc142078fcf811b6a98c2be2eruz7uy.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc1420e8fcf811b6a98c2bfii1lgh1g.mp4',1,0,3,1572946450238,'快快快多看看快快快多看看快快快多看看快快快多看看快快快多看看','快快快多看看快快快多看看快快快多看快快快多看看快快快多看看快快快多看看快快快多看看快快快多看看快快快多看看快快快多看看'),
	(32,'卡拉卡拉','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc142318fcf811b6a98c2c0g3edoxgy.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc1423c8fcf811b6a98c2c1zpjb3cv7.mp4',1,0,3,1572946496286,'卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉','卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉卡拉'),
	(33,'科举考试','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc142568fcf811b6a98c2c2qhwyiu0y.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc1425d8fcf811b6a98c2c30ki4v5ig.mp4',1,0,3,1572946528554,'科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试','科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试科举考试'),
	(34,'刘媛媛——《寒门贵子》','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc142a18fcf811b6a98c2c4lcmy3fty.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc142ac8fcf811b6a98c2c5lc2phni9.mp4',1,0,3,1572946607558,'“命运给你一个比别人低的起点，最想告诉你，让你用你的一生去奋斗出一个绝地反击的故事。”','她借势“寒门再难出贵子”的热门话题代表“寒门”发声红遍网络。“寒门女孩”被15000篇媒体报道，超过861000次视频转发，超过2亿观众讨论 ，实现真正的全民热议。'),
	(35,'哈哈','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc143818fcf811b6a98c2c6rwcs95fu.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc143898fcf811b6a98c2c7xivrsekz.mp4',1,0,2,1572946827506,NULL,NULL),
	(36,'黑胡椒安徽省','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc143ac8fcf811b6a98c2c8ck931fp7.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc143b28fcf811b6a98c2c9mkzeda43.mp4',1,0,2,1572946868200,NULL,NULL),
	(37,'家科技','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc143d38fcf811b6a98c2ca3z7u4ol2.png','http://fs.maidaotech.cn/zyedu/f/2019/11/5/5dc143d88fcf811b6a98c2cblcc0o0gc.mp4',1,0,2,1572946907034,NULL,NULL);

/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
