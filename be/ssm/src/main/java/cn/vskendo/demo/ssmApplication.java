package cn.vskendo.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.vskendo.demo.dao"})
public class ssmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ssmApplication.class, args);
    }

}
