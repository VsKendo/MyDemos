package cn.vskendo.demo.controller;


import cn.vskendo.demo.common.Constants;
import cn.vskendo.demo.common.model.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {

    @RequestMapping("/")
    public String index() {
        return Constants.VERSION;
    }

    @RequestMapping("/version")
    public JsonResult version() {
        return JsonResult.success(Constants.VERSION);
    }

}
