package cn.vskendo.demo.common.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 交易表
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private String createdBy;

    private LocalDateTime createTime;

    private String updatedBy;

    private LocalDateTime updateTime;
}
