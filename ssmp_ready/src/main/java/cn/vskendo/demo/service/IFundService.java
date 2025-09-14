package cn.vskendo.demo.service;

import cn.vskendo.demo.core.common.dto.FundDTO;
import cn.vskendo.demo.core.common.pojo.Fund;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 基金基础信息表 服务类
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
public interface IFundService extends IService<Fund> {
    Fund insertFund(FundDTO fund);

}
