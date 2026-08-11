package cn.vskendo.demo.service.impl;

import cn.vskendo.demo.common.pojo.Fund;
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

}
