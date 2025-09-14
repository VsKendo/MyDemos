package cn.vskendo.demo.controller;

import cn.vskendo.demo.core.common.dto.FundDTO;
import cn.vskendo.demo.core.common.model.JsonResult;
import cn.vskendo.demo.core.common.pojo.Account;
import cn.vskendo.demo.core.common.pojo.Fund;
import cn.vskendo.demo.core.valid.Insert;
import cn.vskendo.demo.core.valid.Update;
import cn.vskendo.demo.service.IFundService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 基金基础信息表 前端控制器
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/fund")
public class FundController {
    private final IFundService fundService;
    
    @RequestMapping("/list")
    public JsonResult list() {
        return JsonResult.success(fundService.list());
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public JsonResult oneGet(@NotNull(message = "用户名查询不能为空") String fundCode) {
        return JsonResult.judge(fundService.lambdaQuery().eq(Fund::getFundCode, fundCode).one());
    }

    @RequestMapping(value = "/one", method = RequestMethod.PUT)
    public JsonResult onePut(@Validated(Insert.class) FundDTO fund) {
        return JsonResult.judge(fundService.insertFund(fund));
    }

//    @RequestMapping(value = "/one", method = RequestMethod.PATCH)
//    public JsonResult oneUpdate(@Validated(Update.class) Fund fund) {
//        return JsonResult.judge(fundService.lambdaUpdate().setEntity(fund).update());
//    }

}
