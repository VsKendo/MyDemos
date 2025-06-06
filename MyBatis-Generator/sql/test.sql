drop database if exists springboot_demo;
create database springboot_demo DEFAULT CHARSET = utf8mb4 DEFAULT COLLATE = utf8mb4_bin;
use springboot_demo;
SET FOREIGN_KEY_CHECKS = 0;
SET default_storage_engine = InnoDB;
create user developer identified by '123456';
GRANT all PRIVILEGES on springboot_demo.* to developer;

CREATE TABLE IF NOT EXISTS `account`
(
    `account_id`    int          NOT NULL AUTO_INCREMENT,
    `username`      varchar(128) NOT NULL COMMENT '登录用户名',
    `name`          varchar(128) NOT NULL default '' COMMENT '用户名字',
    `password`      char(32)     NOT NULL default '' COMMENT '用户密码',
    `email`         varchar(128) NOT NULL default '' COMMENT '邮箱',
    `phone_pre`     varchar(8)   NOT NULL default '86' COMMENT '手机号前缀',
    `phone`         varchar(16)  NOT NULL default '' COMMENT '手机号',
    `address`       varchar(128) NOT NULL default '' COMMENT '地址',
    `account_state` smallint     NOT NULL default 0 COMMENT '账号状态：是否被封号等',
    `created_by`    varchar(18)           default NULL,
    `create_time`   datetime     NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`    varchar(18)           default NULL,
    `update_time`   datetime              default NULL,
    PRIMARY KEY (`account_id`),
    KEY `idx_account_username` (`username`),
    KEY `idx_account_phone` (`phone`)
) comment '账户表';

CREATE TABLE IF NOT EXISTS `debit_card`
(
    `debit_card_id`     int          NOT NULL AUTO_INCREMENT,
    `debit_card_number` varchar(128) NOT NULL default '' COMMENT '银行卡号',
    `CSV`               char(3)      NOT NULL default '' COMMENT '银行卡 CSV',
    `money_amount`      INT UNSIGNED NOT NULL default 0 COMMENT '账户余额，单位为分',
    `card_state`        smallint     NOT NULL default 0 COMMENT '银行卡状态：是否被禁用等',
    `created_by`        varchar(18)           default NULL,
    `create_time`       datetime     NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`        varchar(18)           default NULL,
    `update_time`       datetime              default NULL,
    PRIMARY KEY (`debit_card_id`),
    key `idx_debit_card__number` (`debit_card_number`)
) comment '储蓄表';
