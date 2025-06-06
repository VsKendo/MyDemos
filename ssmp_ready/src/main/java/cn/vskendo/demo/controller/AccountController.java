package cn.vskendo.demo.controller;


import cn.vskendo.demo.common.model.JsonResult;
import cn.vskendo.demo.common.pojo.Account;
import cn.vskendo.demo.core.valid.Insert;
import cn.vskendo.demo.core.valid.Query;
import cn.vskendo.demo.core.valid.Update;
import cn.vskendo.demo.service.IAccountService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
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
        return JsonResult.success(accountService.list());
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public JsonResult oneGet(@NotNull(message = "用户名查询不能为空") String username) {
        return JsonResult.judge(accountService.lambdaQuery().eq(Account::getUsername, username).one());
    }

    @RequestMapping(value = "/one", method = RequestMethod.PUT)
    public JsonResult onePut(@Validated(Insert.class) Account account) {
        return JsonResult.judge(accountService.lambdaUpdate().setEntity(account).update());
    }

    @RequestMapping(value = "/one", method = RequestMethod.PATCH)
    public JsonResult oneUpdate(@Validated(Update.class) Account account) {
        return JsonResult.judge(accountService.lambdaUpdate().setEntity(account).update());
    }

}

