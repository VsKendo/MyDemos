package cn.vskendo.demo.service.impl;

import cn.vskendo.demo.core.common.model.pojo.CreditCard;
import cn.vskendo.demo.dao.mapper.CreditCardMapper;
import cn.vskendo.demo.service.ICreditCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 信用卡 服务实现类
 * </p>
 *
 * @author vskendo
 * @since 2025-09-14
 */
@Service
public class CreditCardServiceImpl extends ServiceImpl<CreditCardMapper, CreditCard> implements ICreditCardService {

}
