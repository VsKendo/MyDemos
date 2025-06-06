package cn.vskendo.demo.controller;


import cn.vskendo.demo.common.constants.HintMessage;
import cn.vskendo.demo.common.model.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {

    @RequestMapping("/")
    public String index() {
        return HintMessage.VERSION;
    }

    @RequestMapping("/version")
    public JsonResult version() {
        return JsonResult.success(HintMessage.VERSION);
    }

}
