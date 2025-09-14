package cn.vskendo.demo.core.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 信用卡
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
@TableName("credit_card")
public class CreditCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "credit_card_id", type = IdType.AUTO)
    private Integer creditCardId;

    /**
     * 银行卡号
     */
    private String creditCardNumber;

    /**
     * 银行卡 CSV
     */
    private String csv;

    /**
     * 账户余额，单位为分
     */
    private Integer moneyAmount;

    /**
     * 信用卡额度，单位为分
     */
    private Integer creditAmount;

    /**
     * 信用卡欠款，单位为分
     */
    private Integer creditOverdraft;

    /**
     * 银行卡状态：是否被禁用等
     */
    private Short cardState;

    private String createdBy;

    private LocalDateTime createTime;

    private String updatedBy;

    private LocalDateTime updateTime;
}
