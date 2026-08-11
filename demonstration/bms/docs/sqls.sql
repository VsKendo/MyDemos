drop database if exists springboot_demo;
create database springboot_demo DEFAULT CHARSET = utf8mb4 DEFAULT COLLATE = utf8mb4_bin;
use springboot_demo;
SET FOREIGN_KEY_CHECKS = 0;
SET default_storage_engine = InnoDB;
## 仅允许localhost登录
create user developer@localhost identified by '123456';
GRANT all PRIVILEGES on springboot_demo.* to developer@localhost;
## 允许所有IP登录
create user developer identified by '123456';
GRANT all PRIVILEGES on springboot_demo.* to developer;

CREATE TABLE `account`
(
    `account_id`    int                NOT NULL AUTO_INCREMENT,
    `username`      varchar(128)       NOT NULL COMMENT '登录用户名',
    `name`          varchar(128)       NOT NULL default '' COMMENT '用户名字',
    `password`      char(32)           NOT NULL default '' COMMENT '用户密码',
    `email`         varchar(128)       NOT NULL default '' COMMENT '邮箱',
    `phone_pre`     mediumint unsigned NOT NULL default 86 COMMENT '手机号前缀',
    `phone`         varchar(16)        NOT NULL default '' COMMENT '手机号',
    `address`       varchar(128)       NOT NULL default '' COMMENT '地址',
    `account_state` TINYINT            NOT NULL default 0 COMMENT '账号状态：是否被禁用等',
    `created_by`    varchar(18)                 default NULL,
    `created_time`  datetime           NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`    varchar(18)                 default NULL,
    `updated_time`  datetime                    default CURRENT_TIMESTAMP,
    PRIMARY KEY (`account_id`),
    KEY `idx_account_username` (`username`),
    KEY `idx_account_phone` (`phone`)
) comment '账户表';
