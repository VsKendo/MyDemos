package cn.vskendo.demo.service.impl;

import cn.vskendo.demo.common.pojo.JavaObject;
import cn.vskendo.demo.dao.JavaObjectMapper;
import cn.vskendo.demo.service.ClassesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class ClassesServiceImpl implements ClassesService {

    private JavaObjectMapper javaObjectMapper;

    @Override
    public JavaObject getOne(String className) {
        log.info(className);
        return javaObjectMapper.selectByPrimaryKey(2);
    }
}
