# ssmp_ready

该项目采用 jdk 17 + springboot 3.5.5 + mybatis 3.0.5 + mybatis plus 3.5.14。

`docs/sqls.sql`中有表结构，`docs/docker.txt`是用 docker 创建数据库的命令。

该项目用 mbp 逆向工程生成了 account 表的实体类，并用 controller 完成了3个功能以展示 mbp 的用法：

```shell
# 随机新建1个account
curl --request POST \
  --url http://localhost:8080/account/generate
# 更新1个 account
curl --request POST \
  --url 'http://localhost:8080/account/update?accountId=4'
# 得到1个 account
curl --request GET \
  --url 'http://localhost:8080/account/get?accountId=4'
```

# 特点

## 自动更新操作记录

实体类继承自`BaseEntity`，并且通过`AutoInsertDateConfig`的设置自动更新`createdTime`, `createdBy`, `updatedTime`和`updatedBy`
