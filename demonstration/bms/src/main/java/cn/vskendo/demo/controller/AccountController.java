package cn.vskendo.demo.controller;

import cn.vskendo.demo.core.common.model.JsonResult;
import cn.vskendo.demo.core.common.model.pojo.Account;
import cn.vskendo.demo.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账户表 前端控制器
 * </p>
 *
 * @author vskendo
 * @since 2025-09-14
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @PostMapping("/generate")
    public JsonResult generate() {
        Account account = new Account();
        account.setName("tester1").setUsername("test").setPassword("123456");
        accountService.save(account);
        return JsonResult.success(account);
    }

    @GetMapping("/get")
    public JsonResult getAccount(int accountId) {
        return JsonResult.success(accountService.getById(accountId));
    }

    @PostMapping("/update")
    public JsonResult getUpdatedAccount(int accountId) {
        Account byId = accountService.getById(accountId);
        accountService.updateById(byId.setUsername("updated"));
        return JsonResult.success(byId);
    }
}
