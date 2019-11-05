create database if not exists zyedu default charset utf8 collate utf8_general_ci;

/* 管理员角色 */
create table role
(
  id          int primary key auto_increment,
  name        varchar(16), /* 角色名称 */
  permissions varchar(1024) /* 角色权限 */
);

insert into role(name, permissions)
values ('超级管理员', '["ROLE_EDIT", "ADMIN_LIST", "ADMIN_EDIT","TRAINER_EDIT","BANNER_EDIT","CAMPUS_EDIT","ARTICLE_EDIT","FORM_QUERY","VIDEO_EDIT"]');

/* 管理员信息表 */
create table admin
(
  id        int primary key auto_increment, /* 主键 */
  username  varchar(16) not null, /* username */
  name      varchar(16), /* 用户名称/备注 */
  password  varchar(32) not null, /* 密码 */
  role_id   int, /* 角色 */
  status    tinyint(4), /* 1.正常 2.停用 */
  signin_at bigint /* 最后登录时间 */
);
/* password A111111+salt */
insert into admin(username, name, password, role_id, status, signin_at)
values ('admin', '超级管理员', 'c4723c465300cf66978f39ccf83f0adc', 1, 1, null);

/* 管理员登录日志 & token 持久化 */
create table admin_session
(
  id        int primary key auto_increment, /* 主键 */
  admin_id  int, /* 关联admin */
  role      varchar(128), /* 管理组名称 */
  name      varchar(128), /* 管理员名称 */
  token     varchar(64), /* token */
  signin_at bigint, /* 登录时间 */
  expire_at bigint, /* 过期时间 */
  ip        varchar(32), /* 登录ip */
  location  varchar(128), /* 登录地址 */
  UNIQUE KEY `token` (`token`)
);


/* 老师 */
CREATE TABLE `trainer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `intro` text,
  `status` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `master_piece` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;


/* form */
CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `mobile` varchar(512) DEFAULT NULL,
  `course` varchar(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- campus
CREATE TABLE `campus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `company` varchar(512) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;


-- article
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- banner
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `img` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- heavy_task
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- video
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `img` varchar(512) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;