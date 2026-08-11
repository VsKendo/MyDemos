# 一、环境安装（Mac）

## 1. 安装依赖工具（Homebrew）

打开终端执行：

```bash
# 安装brew（没装的话）
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# 安装 minikube + kubectl
brew install minikube kubectl

# 安装docker（minikube驱动依赖，Mac推荐docker驱动）
brew install --cask docker
```

## 2. 启动 Docker Desktop

打开 Docker.app，等待完全启动，开启虚拟化（Mac 默认支持）

## 3. 初始化 Minikube

如果内存不足可以使用更少内存。启动时也可以`-p dev-cluster`方便后续切换。

```bash
minikube start \
--driver=docker \
--cpus=4 --memory=8192 \
--ports=127.0.0.1:30543:30543,127.0.0.1:30639:30639,127.0.0.1:30080:30080
--base-image="gcr.io/k8s-minikube/kicbase:v0.0.50" \
--mount \
--mount-string="$HOME/minikube-data:/mnt/host-data"
```

## 新建命名空间 dev

```bash
kubectl create namespace dev
# 设为默认ns，后续不用每次加 -n dev
kubectl config set-context --current --namespace=dev
```

# 二、创建配置文件

## 统一配置密码（K8s Secret，明文不写 yaml）

创建 `secret.yaml` 存储数据库 /redis 密码，环境变量挂载到 Pod

```yaml
# secret.yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-db-secret
type: Opaque
data:
  # 值需要base64编码
  pg-user: YWRtaW4= # admin
  pg-pass: cGFzc3dvcmQ= # password
  redis-pass: cmVkaXNQc3c= # redisPsw
```

## PostgreSQL 18.4-trixie 部署（Deployment+Service + 持久化）

新建 `postgres.yaml`

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: postgres-pvc
  namespace: dev
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: postgres
  namespace: dev
spec:
  replicas: 1
  selector:
    matchLabels:
      app: postgres
  template:
    metadata:
      labels:
        app: postgres
    spec:
      containers:
      - name: postgres
        image: postgres:18.4-trixie
        ports:
        - containerPort: 5432
        env:
        - name: POSTGRES_USER
          valueFrom:
            secretKeyRef:
              name: app-db-secret
              key: pg-user
        - name: POSTGRES_PASSWORD
          valueFrom:
            secretKeyRef:
              name: app-db-secret
              key: pg-pass
        - name: POSTGRES_DB
          value: app_db
        volumeMounts:
        - name: data
          mountPath: /var/lib/postgresql/18/main
      volumes:
      - name: data
        persistentVolumeClaim:
          claimName: postgres-pvc
---
apiVersion: v1
kind: Service
metadata:
  name: postgres-svc
  namespace: dev
spec:
  selector:
    app: postgres
  ports:
  - port: 5432
    targetPort: 5432
    nodePort: 30543
  type: NodePort
```

## Redis 8.8.0-trixie 部署

新建 `redis.yaml`

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-pvc
  namespace: dev
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 2Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: dev
spec:
  replicas: 1
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
      - name: redis
        image: redis:8.8.0-trixie
        command: ["redis-server", "--requirepass", "$(REDIS_PASSWORD)"]
        ports:
        - containerPort: 6379
        env:
        - name: REDIS_PASSWORD
          valueFrom:
            secretKeyRef:
              name: app-db-secret
              key: redis-pass
        volumeMounts:
        - name: data
          mountPath: /data
      volumes:
      - name: data
        persistentVolumeClaim:
          claimName: redis-pvc
---
apiVersion: v1
kind: Service
metadata:
  name: redis-svc
  namespace: dev
spec:
  selector:
    app: redis
  ports:
  - port: 6379
    targetPort: 6379
    nodePort: 30639
  type: NodePort
```

## 执行部署

运行

```bash
kubectl apply -f secret.yaml
kubectl apply -f postgres.yaml
kubectl apply -f redis.yaml

# 查看pod状态，全部Running才算就绪
kubectl get pods
kubectl get svc
```

## 本地连接

连上数据库 `localhost:30543` 库名 app_db 用户 `admin` 密码 `password`

连上Redis `localhost:30639` 密码 `redisPsw`

# SpringBoot 4.1.0 应用改造 & 打包镜像

## 1. application.yml 配置

### local 运行

配置文件如下：

```yaml
spring:
  datasource:
    url: ${PG_URL}
    username: ${PG_USER}
    password: ${PG_PASS}
    driver-class-name: org.postgresql.Driver
  data:
    redis:
      host: ${REDIS_HOST}
      port: ${REDIS_PORT}
      password: ${REDIS_PASS}
```

则需要使用环境变量（如下方的 .env 环境）

```bash
PG_URL="jdbc:postgresql://localhost:30543/app_db",
PG_USER="admin",
PG_PASS="password",
REDIS_HOST="localhost",
REDIS_PORT="30639",
REDIS_PASS="redisPsw",
SPRING_PROFILES_ACTIVE="local"
```

### 集群运行

配置文件如下：

```yaml
spring:
  datasource:
    url: jdbc:postgresql://postgres-svc:5432/app_db
    username: ${PG_USER}
    password: ${PG_PASS}
    driver-class-name: org.postgresql.Driver
  data:
    redis:
      host: redis-svc
      port: 6379
      password: ${REDIS_PASS}
```

配置文件中直接写服务名，用户名密码等变量从 k8s secret 注入，不硬编码。

### 总结

使用一个配置文件，所有都是从环境变量读取是可以的，但是在 springboot.yaml 的 depolyment 文件中要注入所有环境变量

如果使用多个配置文件，记得

* local 时，传入环境变量`spring.profiles.active=local`或者`SPRING_PROFILES_ACTIVE="local"`激活对应配置。

* 集群时，通过`springboot.yaml`传入环境变量 

 ```
  - name: SPRING_PROFILES_ACTIVE
    value: "kube"
 ```

## 2. 项目根目录新建 Dockerfile

```dockerfile
# SpringBoot4.1 推荐jdk21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# 替换为你打包后的jar名称
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
```

## 3. Mac 打包镜像并导入 minikube 镜像仓库

minikube 是独立 docker 环境，本地镜像不会自动识别，必须执行：

```bash
# 1. maven打包jar
./mvnw clean package -DskipTests

# 2. 将本地docker上下文切换到minikube内部docker
eval $(minikube docker-env)

# 3. 构建镜像（镜像名记住，后面yaml要用）
docker build -t springboot-app:4.1.0 .

# 切回本地docker（不需要时执行）
eval $(minikube docker-env -u)
```

# 六、SpringBoot K8s 部署文件 springboot.yaml

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot-app
  namespace: dev
spec:
  replicas: 1
  selector:
    matchLabels:
      app: springboot-app
  template:
    metadata:
      labels:
        app: springboot-app
    spec:
      containers:
      - name: springboot
        image: springboot-app:4.1.0
        imagePullPolicy: Never
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "kube"
        - name: PG_URL
          value: "jdbc:postgresql://postgres-svc:5432/app_db"
        - name: PG_USER
          valueFrom:
            secretKeyRef:
              name: app-db-secret
              key: pg-user
        - name: PG_PASS
          valueFrom:
            secretKeyRef:
              name: app-db-secret
              key: pg-pass
        - name: REDIS_PASS
          valueFrom:
            secretKeyRef:
              name: app-db-secret
              key: redis-pass
        resources:
          requests:
            memory: "512Mi"
            cpu: "500m"
          limits:
            memory: "1Gi"
            cpu: "1000m"
---
apiVersion: v1
kind: Service
metadata:
  name: springboot-svc
  namespace: dev
spec:
  selector:
    app: springboot-app
  ports:
  - port: 8080
    targetPort: 8080
    nodePort: 30080
  type: NodePort
```

部署应用：

```bash
kubectl apply -f springboot.yaml
```

## 访问 SpringBoot 服务

```bash
# 获取minikube内网IP
minikube ip
# 访问地址：http://localhost:30080
```

# 八、常用命令

1. 访问服务：`minikube service springboot-svc`
2. 重新启动上方的集群：`minikube start`
3. 停止集群（保留数据）：`minikube stop`
4. 清空所有集群数据z（重新搭建用）：`minikube delete`
5. 验证集群`kubectl get nodes`
6. 打开k8s可视化面板`minikube dashboard`
7. 切换 docker 环境构建 spring 镜像：`eval $(minikube docker-env)` + docker build
8. 一次性部署全部资源

```bash
kubectl apply -f secret.yaml
kubectl apply -f postgres.yaml
kubectl apply -f redis.yaml
kubectl apply -f springboot.yaml
```

9. 常用排错命令

```bash
# 查看容器日志（启动失败看报错）
kubectl logs pod/[pod名称] -f

# 进入postgres/redis容器调试
kubectl exec -it deployment/postgres -- bash
kubectl exec -it deployment/redis -- redis-cli -a redissPsw

# 查看所有资源
kubectl get all
```

# 关键注意点

1. 提前执行`docker pull postgres:18.4-trixie`、`docker pull redis:8.8.0-trixie` 和`docker pull gcr.io/k8s-minikube/kicbase:v0.0.50`
2. SpringBoot 镜像必须切换到 minikube 的 docker 环境构建，否则 pod 拉取不到镜像

