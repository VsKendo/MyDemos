package cn.vskendo.core;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis plus 逆向工程
 */
public class mbpGeneration {
    public static final String url;
    public static final String username;
    public static final String password;
    public static final List<ClassAnnotationAttributes> lombokAnnotationAttributes;

    static {
        url = "jdbc:mysql://127.0.0.1:3306/springboot_demo?characterEncoding=UTF-8";
        username = "developer";
        password = "123456";
        lombokAnnotationAttributes = new ArrayList<>();
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@Data", "lombok.*"));
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@EqualsAndHashCode(callSuper = true)"));
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@Accessors(chain = true)", "lombok.experimental.Accessors"));
    }


    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder
                        .author("vskendo")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("cn.vskendo.demo")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok(lombokAnnotationAttributes.toArray(new ClassAnnotationAttributes[]{}))
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}