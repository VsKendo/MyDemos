package cn.vskendo.core;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class mybatisPlusGeneration {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/springboot_demo?characterEncoding=UTF-8";
        String username = "developer";
        String password = "123456";
        List<ClassAnnotationAttributes> lombokAnnotationAttributes = new ArrayList<>();
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@Builder", "lombok.Builder"));
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@Getter", "lombok.Getter"));
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@Setter", "lombok.Setter"));
        lombokAnnotationAttributes.add(new ClassAnnotationAttributes("@ToString", "lombok.ToString"));
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