package cn.vskendo.demo.service.impl;

import cn.vskendo.demo.core.common.dto.FundDTO;
import cn.vskendo.demo.core.common.pojo.Fund;
import cn.vskendo.demo.dao.FundMapper;
import cn.vskendo.demo.service.IFundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 基金基础信息表 服务实现类
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, Fund> implements IFundService {

    @Override
    public Fund insertFund(FundDTO dto) {
        Fund data = Fund.builder().fundCode(dto.getFundCode()).fundName(dto.getFundName()).build();
        return lambdaUpdate().setEntity(data).update()?data:null;
    }
}
