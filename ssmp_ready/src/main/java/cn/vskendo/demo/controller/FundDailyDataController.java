package cn.vskendo.demo.controller;

import cn.vskendo.demo.service.IFundDailyDataService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 基金每日行情数据 前端控制器
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/fundDailyData")
public class FundDailyDataController {
    private final IFundDailyDataService fundDailyDataService;


}
