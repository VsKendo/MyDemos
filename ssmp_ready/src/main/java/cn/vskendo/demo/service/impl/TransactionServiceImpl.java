package cn.vskendo.demo.service.impl;

import cn.vskendo.demo.common.pojo.Transaction;
import cn.vskendo.demo.dao.TransactionMapper;
import cn.vskendo.demo.service.ITransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交易表 服务实现类
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {

}
