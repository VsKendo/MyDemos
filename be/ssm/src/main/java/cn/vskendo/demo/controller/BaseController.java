package cn.vskendo.demo.controller;


import cn.vskendo.demo.common.Constants;
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
    @ResponseBody
    public String version() {
        return Constants.VERSION;
    }
}
