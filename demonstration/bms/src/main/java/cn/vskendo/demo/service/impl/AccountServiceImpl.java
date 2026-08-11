package cn.vskendo.demo.service.impl;

import cn.vskendo.demo.core.common.model.pojo.Account;
import cn.vskendo.demo.dao.mapper.AccountMapper;
import cn.vskendo.demo.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author vskendo
 * @since 2025-09-14
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
