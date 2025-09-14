package cn.vskendo.demo.core.common.model.pojo;

import cn.vskendo.demo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * <p>
 * 交易表
 * </p>
 *
 * @author vskendo
 * @since 2025-09-14
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

    @TableId("transaction_id")
    private String transactionId;

    /**
     * 交易流水号
     */
    private String transactionNumber;

    /**
     * 转账者 ID
     */
    private String giverId;

    /**
     * 转账者名字
     */
    private String giverName;

    /**
     * 转账者银行账号
     */
    private String giverBankAccount;

    /**
     * 接受者 ID
     */
    private String receiverId;

    /**
     * 接受者名字
     */
    private String receiverName;

    /**
     * 接受者银行账号
     */
    private String receiverBankAccount;

    /**
     * 钱的数量，单位为分
     */
    private Integer moneyAmount;
}
