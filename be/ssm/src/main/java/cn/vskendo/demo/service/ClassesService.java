package cn.vskendo.demo.service;

import cn.vskendo.demo.common.pojo.JavaObject;

public interface ClassesService {
    /**
     * @param className the name of the class, can be a fuzzy name
     * @return one JavaObject
     */
    JavaObject getOne(String className);
}
