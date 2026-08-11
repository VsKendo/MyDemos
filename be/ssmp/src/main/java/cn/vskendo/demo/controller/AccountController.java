package cn.vskendo.demo.controller;


import cn.vskendo.demo.common.model.JsonResult;
import cn.vskendo.demo.common.pojo.Account;
import cn.vskendo.demo.service.IAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
@Log4j2
public class AccountController {
    private final IAccountService accountService;

    @RequestMapping("/list")
    public JsonResult list() {
        return JsonResult.success();
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public JsonResult oneGet(String username) {
        log.info(username);
        return JsonResult.success();
    }

    @RequestMapping(value = "/one", method = RequestMethod.PUT)
    public JsonResult onePut(Account account) {
        log.info(account.getUsername());
        return JsonResult.judge(accountService.lambdaUpdate().setEntity(account).update());
    }

}

