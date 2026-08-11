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

CREATE TABLE IF NOT EXISTS `credit_card`
(
    `credit_card_id`     int          NOT NULL AUTO_INCREMENT,
    `credit_card_number` varchar(128) NOT NULL default '' COMMENT '银行卡号',
    `CSV`                char(3)      NOT NULL default '' COMMENT '银行卡 CSV',
    `money_amount`       INT UNSIGNED NOT NULL default 0 COMMENT '账户余额，单位为分',
    `credit_amount`      INT UNSIGNED NOT NULL default 0 COMMENT '信用卡额度，单位为分',
    `credit_overdraft`   INT UNSIGNED NOT NULL default 0 COMMENT '信用卡欠款，单位为分',
    `card_state`         smallint     NOT NULL default 0 COMMENT '银行卡状态：是否被禁用等',
    `created_by`         varchar(18)           default NULL,
    `create_time`        datetime     NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`         varchar(18)           default NULL,
    `update_time`        datetime              default NULL,
    PRIMARY KEY (`credit_card_id`),
    key `idx_credit_card__number` (`credit_card_number`)
) comment '信用卡';

CREATE TABLE IF NOT EXISTS `transaction`
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
    `created_by`            varchar(18)           default NULL,
    `create_time`           datetime     NOT NULL default CURRENT_TIMESTAMP,
    `updated_by`            varchar(18)           default NULL,
    `update_time`           datetime              default NULL,
    PRIMARY KEY (`transaction_id`),
    KEY `idx_transaction_create_time` (`create_time`) comment '按照日期查询',
    KEY `idx_transaction_giver_id` (`giver_id`) comment '按照转账人查询',
    KEY `idx_transaction_receiver_id` (`receiver_id`) comment '按照收款人查询'
) comment '交易表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_bin
    PARTITION BY KEY (transaction_id) PARTITIONS 32
;

CREATE TABLE fund
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    fund_code   VARCHAR(20)  NOT NULL UNIQUE COMMENT '基金代码',
    fund_name   VARCHAR(100) NOT NULL COMMENT '基金名称',
    fund_type   VARCHAR(50) COMMENT '基金类型，如混合型、债券型等',
    manager     VARCHAR(100) COMMENT '基金管理人或公司',
    setup_date  DATE COMMENT '成立日期',
    status      TINYINT  DEFAULT 1 COMMENT '状态：1=运作中，0=已清算',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间'
) COMMENT ='基金基础信息表';
CREATE TABLE fund_daily_data
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    fund_code      VARCHAR(20) NOT NULL COMMENT '基金代码',
    date           DATE        NOT NULL COMMENT '交易日期',
    net_value      DECIMAL(10, 4) COMMENT '单位净值',
    growth_rate    DECIMAL(6, 2) COMMENT '涨跌幅（%）',
    estimate_value DECIMAL(10, 4) COMMENT '估值（如有）',
    volume         BIGINT COMMENT '成交量（如有）',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '数据入库时间',
    UNIQUE KEY uk_fund_date (fund_code, date),
    INDEX idx_fund_code (fund_code)
)  COMMENT ='基金每日行情数据';
CREATE TABLE fund_analysis
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    fund_code     VARCHAR(20) NOT NULL COMMENT '基金代码',
    date          DATE        NOT NULL COMMENT '分析日期',
    avg_7d_growth DECIMAL(6, 2) COMMENT '近 7 日平均涨幅',
    max_drawdown  DECIMAL(6, 2) COMMENT '最大回撤（%）',
    ma_20         DECIMAL(10, 4) COMMENT '20 日移动平均',
    strategy_tag  VARCHAR(50) COMMENT '策略标签，如高波动、稳健等',
    UNIQUE KEY uk_analysis (fund_code, date),
    INDEX idx_analysis_fund (fund_code)
)  COMMENT ='基金分析指标表';
CREATE TABLE fund_investment
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    fund_code     VARCHAR(20)    NOT NULL COMMENT '基金代码',
    invest_date   DATE           NOT NULL COMMENT '买入日期',
    invest_amount DECIMAL(12, 2) NOT NULL COMMENT '投入金额（总金额）',
    unit_price    DECIMAL(10, 4) NOT NULL COMMENT '买入时单位净值',
    share         DECIMAL(12, 4) NOT NULL COMMENT '买入的份额 = 投入金额 / 单位净值',
    note          VARCHAR(255) COMMENT '备注（如模拟组合名、策略说明）',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='模拟投资记录表';
# SELECT i.fund_code,
#        d.date,
#        i.share,
#        d.net_value,
#        ROUND(i.share * d.net_value, 2)                   AS market_value,
#        ROUND(i.share * d.net_value - i.invest_amount, 2) AS profit
# FROM fund_investment i
#          JOIN
#      fund_daily_data d
#      ON
#          i.fund_code = d.fund_code
# WHERE d.date >= i.invest_date
# ORDER BY i.fund_code, d.date;
