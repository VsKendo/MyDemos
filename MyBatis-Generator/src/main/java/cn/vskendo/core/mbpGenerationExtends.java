package cn.vskendo.core;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;

import java.nio.file.Paths;

import static cn.vskendo.core.mbpGeneration.*;

/**
 * mybatis plus 逆向工程 (实体类继承base entity)
 */
public class mbpGenerationExtends {
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
                        .entity("core.common.model.pojo")
                        .mapper("dao.mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .disableSerialVersionUID()
                        .superClass("cn.vskendo.demo.entity.BaseEntity")
                        .addIgnoreColumns("created_by", "created_time", "updated_by", "updated_time")
                        .enableLombok(lombokAnnotationAttributes.toArray(new ClassAnnotationAttributes[]{}))
                        .controllerBuilder()
                        .enableRestStyle()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}