package cn.vskendo.demo.controller;


import cn.vskendo.demo.common.model.JsonResult;
import cn.vskendo.demo.service.ClassesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/classes")
@Log4j2
public class ClassesController {
    ClassesService classesService;

    @RequestMapping("/list")
    public JsonResult list() {
        return JsonResult.success();
    }

    @RequestMapping("/one")
    public JsonResult one(String className) {
        log.info(className);
        return JsonResult.success(classesService.getOne("123"));
    }
}

