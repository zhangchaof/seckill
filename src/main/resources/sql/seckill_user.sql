CREATE TABLE `seckill_user` (
	`id` BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`nickname` VARCHAR (255) NOT NULL,
	`password` VARCHAR (32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt) + salt)',
	`salt` VARCHAR (10) DEFAULT NULL,
	`head` VARCHAR (128) DEFAULT NULL COMMENT '头像，云存储的ID',
	`register_date` datetime DEFAULT NULL COMMENT '注册时间',
	`last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
	`login_count` INT (11) DEFAULT NULL COMMENT '登录次数',
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT '秒杀用户表';