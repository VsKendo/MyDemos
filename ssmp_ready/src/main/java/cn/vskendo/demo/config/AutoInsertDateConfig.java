package cn.vskendo.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * 自动注入 BaseEntity 中的字段
 * 此处用的是 LocalDateTime 因为 BaseEntity 实体类用的是 LocalDateTime
 * 如果是 Java 8 之前要使用 java.util.Date
 */
@Configuration
public class AutoInsertDateConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(
                metaObject,
                "createdTime", // 实体类中的字段名（注意驼峰命名）
                LocalDateTime.class,
                LocalDateTime.now() // 填充的值（当前时间）
        );
        this.strictInsertFill(
                metaObject,
                "createdBy", // 实体类中的字段名（注意驼峰命名）
                String.class,
                "creator" // 填充的值（当前操作者），在生产中应该查出当前用户信息并将用户名填到此处
        );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(
                metaObject,
                "updatedTime", // 实体类中的字段名（注意驼峰命名）
                LocalDateTime.now() // 填充的值（当前时间）
        );
        this.fillStrategy(
                metaObject,
                "updatedBy", // 实体类中的字段名（注意驼峰命名）
                "updater" // 填充的值（当前操作者），在生产中应该查出当前用户信息并将用户名填到此处
        );
    }
}
