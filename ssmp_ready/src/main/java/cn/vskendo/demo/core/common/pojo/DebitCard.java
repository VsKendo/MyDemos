package cn.vskendo.demo.common.pojo;

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
 * 储蓄表
 * </p>
 *
 * @author vskendo
 * @since 2025-06-07
 */
@Getter
@Setter
@Builder
@ToString
@TableName("debit_card")
public class DebitCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "debit_card_id", type = IdType.AUTO)
    private Integer debitCardId;

    /**
     * 银行卡号
     */
    private String debitCardNumber;

    /**
     * 银行卡 CSV
     */
    private String csv;

    /**
     * 账户余额，单位为分
     */
    private Integer moneyAmount;

    /**
     * 银行卡状态：是否被禁用等
     */
    private Short cardState;

    private String createdBy;

    private LocalDateTime createTime;

    private String updatedBy;

    private LocalDateTime updateTime;
}
