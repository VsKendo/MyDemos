# MyDemos
为了方便自己查阅并帮助他人，这里写了很多项目的 Demo，帮助你快速上手后端的项目。

项目均使用 JDK21，切换成1.8的话可能需要降低 Spring 等框架的版本号。

## uiltality

1. MyBatis-Generator：Mybatis 和 Mybatis Plus 逆向工程、代码生成器。在使用 sql 文件建库建表后，可以使用代码生成器快速生成实体类和 Mapper，适合学习和开发使用。

## ee

1. minikube: 如何用 minikube 创建一个 dev 环境的 k8s，并且初始化最基础的关系和非关系数据库。
2. docker：如何用 docker compose 创建一个带有关系和非关系数据库的环境。

## be

### 简单例子

1. ssm【待更新】：springboot web + mybatis
2. ssmp【待更新】：springboot web + mybatis plus
3. ssh【计划中】：springboot webflux + springboot jpa + hibernate 
4. shiro【计划中】：springboot web + shiro
5. spring_security【计划中】：springboot webflux + spring security

### demonstration【仍在开发中】

1. BMS - Bank Management System【计划中】：以一个简单的银行系统为 demo 做的比较传统的项目，适用于大多数中小型公司的架构。
   - 后端：springboot web + mybatis plus + Shiro
   - 前端：Vue 3 + Vite + TypeScript + Element Plus
   - 末端 (Docker Compose)：MySql + Redis
2. STS - Stock Tracking System【计划中】：以一个稍微复杂的股票/基金追踪系统为 demo 做的中大型项目。
   - 后端：springboot webflux + springboot jpa + hibernate + spring security
   - 前端：Angular
   - 末端(K8s) ：PostgreSql + Redis + AKTools(AKShare) 
3. AS - AuthSystem【计划中】：以一个复杂的授权系统为 demo 做的云原生微服务项目。
   - 后端：springboot web + spring cloud gateway + spring security + mybatis flex
   - 前端：React.js + Next.js
   - 末端 (K8s)：MySql + Redis + Istio
