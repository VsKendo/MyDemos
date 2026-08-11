# 0. 介绍

有多种 docker-compose 启动文件，适用于不同情况

# 1.单文件版本

运行`docker compose -f docker-compose.yml up -d`即可运行目录下的文件，从而运行`redis + postgres + plantuml + swagger-ui`。在真正运行前可以先 `docker pull redis`等把镜像拉下运行更快。

# 2.多文件版本

进入文件夹`docker-compose`，运行`docker compose -f cloud-dev.yml up -d`即可运行`redis + mysql + nacos`。这个多文件版本的好处就是存储的密码放在 `env`文件夹中，同时`nacos`启动的时候需要执行的初始化数据库脚本`mysql-schema.sql`也会执行（如果版本不对则执行`mysql-init.sh`重新下载最新脚本执行即可）。

如果你不需要`nacos`，还有`mysql_redis.yaml`和`mysql.yaml`供你挑选。