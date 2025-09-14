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

CREATE TABLE IF NOT EXISTS `account`
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

CREATE TABLE IF NOT EXISTS `debit_card`
(
    `debit_card_id`     int          NOT NULL AUTO_INCREMENT,
    `debit_card_number` varchar(128) NOT NULL default '' COMMENT '银行卡号',
    `CSV`               char(3)      NOT NULL default '' COMMENT '银行卡 CSV',
    `money_amount`      INT UNSIGNED NOT NULL default 0 COMMENT '账户余额，单位为分',
    `card_state`        smallint     NOT NULL default 0 COMMENT '银行卡状态：是否被禁用等',
    `created_by`    varchar(18)                 default NULL,
    `created_time`  datetime           NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`    varchar(18)                 default NULL,
    `updated_time`  datetime                    default CURRENT_TIMESTAMP,
    PRIMARY KEY (`debit_card_id`),
    key `idx_debit_card__number` (`debit_card_number`)
    ) comment '储蓄表';

CREATE TABLE IF NOT EXISTS `credit_card`
(
    `credit_card_id`     int          NOT NULL AUTO_INCREMENT,
    `credit_card_number` varchar(128) NOT NULL default '' COMMENT '银行卡号',
    `CSV`                char(3)      NOT NULL default '' COMMENT '银行卡 CSV',
    `money_amount`       INT UNSIGNED NOT NULL default 0 COMMENT '账户余额，单位为分',
    `credit_amount`      INT UNSIGNED NOT NULL default 0 COMMENT '信用卡额度，单位为分',
    `credit_overdraft`   INT UNSIGNED NOT NULL default 0 COMMENT '信用卡欠款，单位为分',
    `card_state`         smallint     NOT NULL default 0 COMMENT '银行卡状态：是否被禁用等',
    `created_by`    varchar(18)                 default NULL,
    `created_time`  datetime           NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`    varchar(18)                 default NULL,
    `updated_time`  datetime                    default CURRENT_TIMESTAMP,
    PRIMARY KEY (`credit_card_id`),
    key `idx_credit_card__number` (`credit_card_number`)
    ) comment '信用卡';

CREATE TABLE `transaction`
(
    `transaction_id`        varchar(18)  NOT NULL,
    `transaction_number`    varchar(18)  NOT NULL comment '交易流水号',
    `giver_id`              varchar(18)  NOT NULL comment '转账者 ID',
    `giver_name`            varchar(18)  NOT NULL comment '转账者名字',
    `giver_bank_account`    varchar(18)  NOT NULL comment '转账者银行账号',
    `receiver_id`           varchar(18)  NOT NULL comment '接受者 ID',
    `receiver_name`         varchar(18)  NOT NULL comment '接受者名字',
    `receiver_bank_account` varchar(18)  NOT NULL comment '接受者银行账号',
    `money_amount`          INT UNSIGNED NOT NULL comment '钱的数量，单位为分',
    `created_by`    varchar(18)                 default NULL,
    `created_time`  datetime           NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`    varchar(18)                 default NULL,
    `updated_time`  datetime                    default CURRENT_TIMESTAMP,
    PRIMARY KEY (`transaction_id`),
    KEY `idx_transaction_created_time` (`created_time`) comment '按照日期查询',
    KEY `idx_transaction_giver_id` (`giver_id`) comment '按照转账人查询',
    KEY `idx_transaction_receiver_id` (`receiver_id`) comment '按照收款人查询'
) comment '交易表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_bin
    PARTITION BY KEY (transaction_id) PARTITIONS 32
;
